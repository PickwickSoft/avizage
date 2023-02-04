package org.pickwicksoft.avizage.service.dto

import org.pickwicksoft.avizage.domain.model.BillItem
import java.time.Instant

data class BillDto(
    val id: Int? = null,
    val number: String,
    val created: Instant? = Instant.now(),
    val totalPrice: Double = 0.0,
    val userName: String,
    val products: MutableSet<BillItem> = mutableSetOf()
)
