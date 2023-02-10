package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductRepository : JpaRepository<Product, Long> {

    fun findOneByBarCode(barCode: Long): Optional<Product>
}
