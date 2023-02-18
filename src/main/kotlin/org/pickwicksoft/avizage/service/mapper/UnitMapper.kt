package org.pickwicksoft.avizage.service.mapper

import org.mapstruct.*
import org.pickwicksoft.avizage.domain.entity.Unit
import org.pickwicksoft.avizage.service.dto.UnitDto
import org.springframework.stereotype.Service

@Service
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
abstract class UnitMapper {

    abstract fun toEntity(unitDto: UnitDto): Unit

    fun toDto(unit: Unit): UnitDto {
        return UnitDto(unit.name, unit.code, unit.id)
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(unitDto: UnitDto, @MappingTarget unit: Unit): Unit
}
