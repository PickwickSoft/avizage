package org.pickwicksoft.avizage.service.dto

import java.io.Serializable
import java.time.Instant

/**
 * A DTO for the {@link org.pickwicksoft.avizage.domain.entity.Sale} entity
 */
data class SaleDto(
    val date: Instant? = Instant.now(),
    val user: String,
    val storage: String,
    val itemName: String,
    val unitPrice: Double,
    val quantity: Double,
) : Serializable
