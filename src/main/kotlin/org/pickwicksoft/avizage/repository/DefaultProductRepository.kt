package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.DefaultProduct
import org.springframework.data.jpa.repository.JpaRepository

interface DefaultProductRepository : JpaRepository<DefaultProduct, Long>
