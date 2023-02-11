package org.pickwicksoft.avizage.service

import org.pickwicksoft.avizage.domain.entity.Bill
import org.pickwicksoft.avizage.domain.entity.Category
import org.pickwicksoft.avizage.domain.entity.Product
import org.pickwicksoft.avizage.domain.entity.Sale
import org.pickwicksoft.avizage.domain.model.Cart
import org.pickwicksoft.avizage.domain.model.CartItem
import org.pickwicksoft.avizage.repository.*
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

@Service
class SaleService(
    private val saleRepository: SaleRepository,
    private val billRepository: BillRepository,
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val storageRepository: StorageRepository,
    private val userService: UserService
) {
    fun sellCartFromShop(cart: Cart): Bill {
        val sales = mutableListOf<Sale>()
        cart.items.forEach { item ->
            val sale = createSaleFromCartItem(item)
            val (product, category) = getProductAndCategoryById(item.id)
            sale.product = product
            sale.category = category
            sales.add(saleRepository.save(sale))
        }
        return createBillFromSales(sales)
    }

    fun getTodaySales(): List<Sale> {
        val now = LocalDateTime.now()
        val startOfDay = LocalDateTime
            .of(now.year, now.month, now.dayOfMonth, 0, 0)
            .atZone(ZoneId.systemDefault()).toInstant()
        return saleRepository.findAll().filter { sale ->
            sale.date!!.isAfter(startOfDay)
        }
    }

    fun getSalesOf(day: Instant): List<Sale> {
        val requestedDay = LocalDateTime.ofInstant(day, ZoneId.systemDefault())
        val startOfDay = LocalDateTime
            .of(requestedDay.year, requestedDay.month, requestedDay.dayOfMonth, 0, 0, 0)
            .atZone(ZoneId.systemDefault()).toInstant()
        val startOfNextDay = startOfDay.plus(1, ChronoUnit.DAYS)

        return saleRepository.findAll().filter { sale ->
            sale.date!!.isAfter(startOfDay) && sale.date!!.isBefore(startOfNextDay)
        }
    }

    private fun createSaleFromCartItem(item: CartItem): Sale = Sale(
        user = userService.getUserWithAuthorities().orElseThrow(),
        storage = storageRepository.getReferenceById(1),
        unitPrice = item.price,
        quantity = item.qty
    )

    private fun getProductAndCategoryById(id: Long): Pair<Product?, Category?> {
        val product = productRepository.findById(id).orElse(null)
        product.takeIf { it != null }?.let {
            return Pair(it, it.category)
        }
        return Pair(null, categoryRepository.findById(id).orElse(null))
    }

    private fun createBillFromSales(sales: MutableList<Sale>): Bill {
        return billRepository.save(
            Bill(
                number = "1",
                value = sales.sumOf { it.unitPrice * it.quantity },
                user = userService.getUserWithAuthorities().orElseThrow(),
                sales = sales.toMutableSet()
            )
        )
    }
}
