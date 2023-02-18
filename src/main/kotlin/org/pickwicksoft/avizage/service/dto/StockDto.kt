package org.pickwicksoft.avizage.service.dto

import java.io.Serializable

/**
 * A DTO for the {@link org.pickwicksoft.avizage.domain.entity.Stock} entity
 */
data class StockDto(
    val productName: String,
    val productUnitId: String,
    val productCategoryName: String,
    val productCategoryId: Long,
    val productBarCode: Long? = null,
    val productId: Long,
    val storageName: String,
    val storageId: Int,
    val quantity: Double,
    val purchasePrice: Double,
    val salePrice: Double,
    val id: Int
) : Serializable
