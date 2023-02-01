package org.pickwicksoft.avizage.service.mapper

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.pickwicksoft.avizage.domain.entity.Bill
import org.pickwicksoft.avizage.domain.entity.Sale
import org.pickwicksoft.avizage.domain.model.CartItem
import org.pickwicksoft.avizage.service.UserService
import org.pickwicksoft.avizage.service.dto.BillDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
abstract class BillMapper {

    @Autowired
    lateinit var userService: UserService

    fun toEntity(billDto: BillDto): Bill {
        return Bill(
            id = billDto.id,
            number = billDto.number,
            date = billDto.created,
            value = billDto.totalPrice,
            user = userService.getUserWithAuthoritiesByLogin(billDto.userName).orElseThrow()
        )
    }

    fun toDto(bill: Bill): BillDto {
        return BillDto(
            id = bill.id,
            number = bill.number,
            created = bill.date,
            totalPrice = bill.value,
            userName = bill.user.login.orEmpty(),
            products = salesToCartItems(bill.sales)
        )
    }

    private fun salesToCartItems(sales: MutableSet<Sale>): MutableSet<CartItem> {
        val cartItems = mutableSetOf<CartItem>()
        sales.forEach { sale ->
            cartItems.add(CartItem(getIdOfProduct(sale), sale.quantity, sale.unitPrice))
        }
        return cartItems
    }

    private fun getIdOfProduct(sale: Sale): Long {
        return sale.product?.id ?: sale.category?.id ?: throw Exception("Product and Category are null")
    }
}
