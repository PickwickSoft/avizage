package org.pickwicksoft.avizage.web.rest

import org.pickwicksoft.avizage.repository.BillRepository
import org.pickwicksoft.avizage.service.dto.BillDto
import org.pickwicksoft.avizage.service.mapper.BillMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/shop")
class BillingResource(
    val billRepository: BillRepository,
    val billMapper: BillMapper
) {
    @GetMapping("/bill/{id}")
    fun getBill(@PathVariable id: Int): Optional<BillDto> = billRepository.findById(id).map(billMapper::toDto)
}
