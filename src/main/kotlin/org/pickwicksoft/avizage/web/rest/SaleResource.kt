package org.pickwicksoft.avizage.web.rest

import org.pickwicksoft.avizage.domain.entity.Sale
import org.pickwicksoft.avizage.domain.model.Cart
import org.pickwicksoft.avizage.domain.model.CartItem
import org.pickwicksoft.avizage.service.SaleService
import org.pickwicksoft.avizage.service.dto.BillDto
import org.pickwicksoft.avizage.service.dto.SaleDto
import org.pickwicksoft.avizage.service.dto.SalesReportDto
import org.pickwicksoft.avizage.service.mapper.BillMapper
import org.pickwicksoft.avizage.service.mapper.SaleMapper
import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.*
import javax.validation.Valid
import kotlin.math.abs

@RestController
@RequestMapping("/api/shop")
class SaleResource(
    private val saleService: SaleService,
    private val billMapper: BillMapper,
    private val saleMapper: SaleMapper
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/sale")
    fun sale(@Valid @RequestBody cart: MutableSet<CartItem>): BillDto {
        return billMapper.toDto(saleService.sellCartFromShop(Cart(items = cart)))
    }

    @GetMapping("/sales/today")
    fun getTodaySales(): SalesReportDto {
        log.debug("REST request to get today sales")
        val sales = saleService.getTodaySales()
        return salesReportDto(sales)
    }

    @GetMapping("/sales/of")
    fun getSalesOf(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) day: LocalDate): SalesReportDto {
        log.debug("GET request to get sales for day")
        val requestedDay = LocalDateTime.of(day, LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant()
        val sales = saleService.getSalesOf(requestedDay)

        return salesReportDto(sales)
    }

    @GetMapping("/sales")
    fun getSalesInRange(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate?,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate?
    ): SalesReportDto {
        log.debug("GET request to get sales in range from $startDate to $endDate")
        val fromDate = startDate?.let {
            LocalDateTime.of(it, LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant()
        }
        val toDate = endDate?.let {
            LocalDateTime.of(endDate, LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant()
        }
        val sales = saleService.getSalesInRange(fromDate, toDate)

        return salesReportDto(sales)
    }

    private fun salesReportDto(sales: List<Sale>): SalesReportDto {

        val itemsMap = sales
            .groupBy { Pair(it.category, it.quantity / abs(it.quantity)) }

        val reportItems: MutableSet<SaleDto> = mutableSetOf()
        var total = 0.0

        for (item in itemsMap) {
            var firstSaleInCategory: Sale = item.value[0]
            val sum = item.value
                .map { sale -> (sale.quantity * sale.unitPrice) }
                .reduce { acc, d -> acc + d }

            val qty = item.value
                .map { saleDto -> saleDto.quantity }
                .reduce { acc, d -> acc + d }

            reportItems.add(SaleDto(firstSaleInCategory.date, firstSaleInCategory.user.login!!, firstSaleInCategory.storage.name, item.key!!.first!!.name, sum, qty))
            total += sum
        }

        return SalesReportDto(reportItems.sortedBy { it.itemName }.toList(), total)
    }
}
