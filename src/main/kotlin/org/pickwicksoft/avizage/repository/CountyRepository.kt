package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.County
import org.springframework.data.jpa.repository.JpaRepository

interface CountyRepository : JpaRepository<County, Int>
