package org.pickwicksoft.avizage.service.mapper

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.pickwicksoft.avizage.domain.entity.Bill
import org.pickwicksoft.avizage.domain.entity.Sale
import org.pickwicksoft.avizage.domain.model.BillItem
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
            products = salesToBillItems(bill.sales)
        )
    }

    private fun salesToBillItems(sales: MutableSet<Sale>): MutableSet<BillItem> {
        val billItems = mutableSetOf<BillItem>()
        sales.forEach { sale ->
            if (sale.product != null) {
                billItems.add(BillItem(getIdOfProduct(sale), sale.product!!.name, sale.quantity, sale.unitPrice))
            } else if (sale.category != null) {
                billItems.add(BillItem(getIdOfProduct(sale), sale.category!!.name, sale.quantity, sale.unitPrice))
            }
        }
        return billItems
    }

    private fun getIdOfProduct(sale: Sale): Long {
        return sale.product?.id ?: sale.category?.id ?: throw Exception("Product and Category are null")
    }
}
