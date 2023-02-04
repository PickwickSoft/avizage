package org.pickwicksoft.avizage.service.dto

import java.io.Serializable

/**
 * A DTO for the {@link org.pickwicksoft.avizage.domain.entity.Category} entity
 */
data class CategoryDto(
    val name: String? = null,
    val iconName: String? = null,
    val id: Long? = null
) : Serializable
