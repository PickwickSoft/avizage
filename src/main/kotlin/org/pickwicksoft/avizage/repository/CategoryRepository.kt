package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>
