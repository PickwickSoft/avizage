package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Country
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository : JpaRepository<Country, Int>
