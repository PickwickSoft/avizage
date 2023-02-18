package org.pickwicksoft.avizage.service.dto

import java.io.Serializable

/**
 * A DTO for the {@link org.pickwicksoft.avizage.domain.entity.Unit} entity
 */
data class UnitDto(val name: String? = null, val code: String? = null, val id: Int? = null) : Serializable
