package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
