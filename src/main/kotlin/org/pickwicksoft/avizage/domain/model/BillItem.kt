package org.pickwicksoft.avizage.domain.model

data class BillItem(
    val id: Long,
    val name: String,
    val qty: Double,
    val price: Double
)
