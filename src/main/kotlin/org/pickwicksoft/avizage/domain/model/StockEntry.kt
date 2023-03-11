package org.pickwicksoft.avizage.domain.model

class StockEntry(
    val productId: Long?,
    val name: String,
    val description: String?,
    val categoryId: Int,
    val purchasePrice: Double,
    val salePrice: Double,
    val quantity: Double,
    val unitId: Int,
    val iconName: String?,
    val barCode: Long?
)
