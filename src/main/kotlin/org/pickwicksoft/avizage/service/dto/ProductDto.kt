package org.pickwicksoft.avizage.service.dto

import java.io.Serializable

/**
 * A DTO for the {@link org.pickwicksoft.avizage.domain.entity.Product} entity
 */
data class ProductDto(
    val name: String? = null,
    val iconName: String? = null,
    val price: Double?,
    val id: Long? = null
) : Serializable
