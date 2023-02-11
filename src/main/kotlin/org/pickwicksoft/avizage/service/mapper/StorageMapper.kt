package org.pickwicksoft.avizage.service.mapper

import org.mapstruct.*
import org.pickwicksoft.avizage.domain.entity.Storage
import org.pickwicksoft.avizage.service.dto.StorageDto
import org.springframework.stereotype.Service

@Service
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
abstract class StorageMapper {

    abstract fun toEntity(storageDto: StorageDto): Storage

    abstract fun toDto(storage: Storage): StorageDto

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(storageDto: StorageDto, @MappingTarget storage: Storage): Storage
}
