package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Supplier
import org.springframework.data.jpa.repository.JpaRepository

interface SupplierRepository : JpaRepository<Supplier, Int>
