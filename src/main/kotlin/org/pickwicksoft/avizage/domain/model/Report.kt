package org.pickwicksoft.avizage.domain.model

data class Report(
    val items: List<ReportItem>,
    val total: Double
)
