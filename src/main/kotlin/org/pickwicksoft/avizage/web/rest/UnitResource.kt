package org.pickwicksoft.avizage.web.rest

import org.pickwicksoft.avizage.repository.UnitRepository
import org.pickwicksoft.avizage.service.dto.UnitDto
import org.pickwicksoft.avizage.service.mapper.UnitMapper
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/unit")
class UnitResource(
    private val unitRepository: UnitRepository,
    private val unitMapper: UnitMapper
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("")
    fun getAllUnits(): List<UnitDto> {
        log.debug("REST request to get all Units")
        return unitRepository.findAll().map(unitMapper::toDto)
    }
}
