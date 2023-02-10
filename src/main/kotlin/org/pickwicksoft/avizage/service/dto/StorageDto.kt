package org.pickwicksoft.avizage.service.dto

import java.io.Serializable

/**
 * A DTO for the {@link org.pickwicksoft.avizage.domain.entity.Storage} entity
 */
data class StorageDto(
    val name: String,
    val description: String,
    val id: Int
) : Serializable
