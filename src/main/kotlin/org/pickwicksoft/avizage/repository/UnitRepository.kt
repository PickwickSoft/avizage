package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Unit
import org.springframework.data.jpa.repository.JpaRepository

interface UnitRepository : JpaRepository<Unit, Int>
