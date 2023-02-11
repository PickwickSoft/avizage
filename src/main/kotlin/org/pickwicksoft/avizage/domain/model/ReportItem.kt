package org.pickwicksoft.avizage.domain.model

data class ReportItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    val totalPrice: Double,
)
