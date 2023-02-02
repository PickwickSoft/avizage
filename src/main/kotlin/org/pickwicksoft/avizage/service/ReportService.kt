package org.pickwicksoft.avizage.service

import org.pickwicksoft.avizage.domain.entity.Sale
import org.pickwicksoft.avizage.repository.SaleRepository
import org.springframework.stereotype.Service

@Service
class ReportService(
    private val saleRepository: SaleRepository
) {
    fun getSalesOfToday(): List<Sale> {
        return saleRepository.findAll()
    }
}
