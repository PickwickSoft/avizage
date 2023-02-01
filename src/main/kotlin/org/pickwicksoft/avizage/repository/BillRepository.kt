package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Bill
import org.springframework.data.jpa.repository.JpaRepository

interface BillRepository : JpaRepository<Bill, Int>
