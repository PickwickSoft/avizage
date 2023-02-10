package org.pickwicksoft.avizage.service.dto

import java.io.Serializable

/**
 * A DTO for the {@link org.pickwicksoft.avizage.domain.entity.Product} entity
 */
data class ProductDto(
    val name: String,
    val iconName: String,
    val description: String? = null,
    val unitName: String,
    val unitCode: String,
    val categoryName: String,
    val barCode: Long? = null,
    val id: Long? = null
) : Serializable
