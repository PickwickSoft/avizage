package org.pickwicksoft.avizage.repository

import org.pickwicksoft.avizage.domain.entity.Authority
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Spring Data JPA repository for the [Authority] entity.
 */

interface AuthorityRepository : JpaRepository<Authority, String>
