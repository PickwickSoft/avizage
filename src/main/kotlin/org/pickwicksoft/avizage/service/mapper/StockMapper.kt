package org.pickwicksoft.avizage.service.mapper

import org.mapstruct.*
import org.pickwicksoft.avizage.domain.entity.Stock
import org.pickwicksoft.avizage.service.dto.StockDto
import org.springframework.stereotype.Service

@Service
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
abstract class StockMapper {

    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.unit.code", target = "productUnitCode")
    @Mapping(source = "product.category.name", target = "productCategoryName")
    @Mapping(source = "product.barCode", target = "productBarCode")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "storage.name", target = "storageName")
    @Mapping(source = "storage.id", target = "storageId")
    abstract fun toDto(stock: Stock): StockDto

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(stockDto: StockDto, @MappingTarget stock: Stock): Stock
}
