package org.pickwicksoft.avizage.service.dto

data class SalesReportDto(
    val items: List<SaleDto>,
    val total: Double
)
