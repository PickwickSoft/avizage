package org.pickwicksoft.avizage.domain.model

data class Cart(
    val items: MutableSet<CartItem> = mutableSetOf()
)
