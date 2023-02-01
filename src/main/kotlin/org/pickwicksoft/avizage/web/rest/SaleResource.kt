package org.pickwicksoft.avizage.web.rest

import org.pickwicksoft.avizage.domain.model.Cart
import org.pickwicksoft.avizage.domain.model.CartItem
import org.pickwicksoft.avizage.service.SaleService
import org.pickwicksoft.avizage.service.dto.BillDto
import org.pickwicksoft.avizage.service.mapper.BillMapper
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/shop")
class SaleResource(
    private val saleService: SaleService,
    private val billMapper: BillMapper
) {
    @PostMapping("/sale")
    fun sale(@Valid @RequestBody cart: MutableSet<CartItem>): BillDto {
        return billMapper.toDto(saleService.sellCartFromShop(Cart(items = cart)))
    }
}
