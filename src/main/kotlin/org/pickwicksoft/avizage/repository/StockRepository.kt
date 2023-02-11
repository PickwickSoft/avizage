package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Int> {
    fun findAllByProductId(productId: Long): MutableSet<Stock>
}
