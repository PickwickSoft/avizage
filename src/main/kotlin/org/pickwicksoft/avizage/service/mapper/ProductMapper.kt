package org.pickwicksoft.avizage.service.mapper

import org.mapstruct.*
import org.pickwicksoft.avizage.domain.entity.Product
import org.pickwicksoft.avizage.service.dto.ProductDto
import org.springframework.stereotype.Service

@Service
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
abstract class ProductMapper {

    @Mappings(
        Mapping(expression = "java(unitNameOf(product))", target = "unitName"),
        Mapping(expression = "java(unitCodeOf(product))", target = "unitCode"),
        Mapping(expression = "java(categoryNameOf(product))", target = "categoryName")
    )
    abstract fun toDto(product: Product): ProductDto

    fun unitNameOf(product: Product): String = product.unit.name
    fun unitCodeOf(product: Product): String = product.unit.code
    fun categoryNameOf(product: Product): String = product.category.name
}
