package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Storage
import org.springframework.data.jpa.repository.JpaRepository

interface StorageRepository : JpaRepository<Storage, Int>
