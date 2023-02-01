package org.pickwicksoft.avizage.service.mapper

import org.mapstruct.*
import org.pickwicksoft.avizage.domain.entity.Category
import org.pickwicksoft.avizage.service.dto.CategoryDto
import org.springframework.stereotype.Service

@Service
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
abstract class CategoryMapper {

    abstract fun toEntity(categoryDto: CategoryDto): Category

    fun toDto(category: Category): CategoryDto {
        return CategoryDto(
            id = category.id,
            name = category.name,
            iconName = category.iconName,
        )
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(categoryDto: CategoryDto, @MappingTarget category: Category): Category
}
