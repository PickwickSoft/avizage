package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.DefaultCategory
import org.springframework.data.jpa.repository.JpaRepository

interface DefaultCategoryRepository : JpaRepository<DefaultCategory, Long>
