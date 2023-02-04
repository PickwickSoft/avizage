package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Sale
import org.springframework.data.jpa.repository.JpaRepository

interface SaleRepository : JpaRepository<Sale, Int>
