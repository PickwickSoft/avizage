package org.pickwicksoft.avizage.service.mapper

import org.mapstruct.*
import org.pickwicksoft.avizage.domain.entity.DefaultProduct
import org.pickwicksoft.avizage.repository.StockRepository
import org.pickwicksoft.avizage.service.dto.DefaultProductDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
abstract class DefaultProductMapper {

    @Autowired
    lateinit var stockRepository: StockRepository

    @Mapping(target = "price", expression = "java(stocksToPrice(defaultProduct))")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "iconName", source = "product.iconName")
    @Mapping(target = "id", source = "product.id")
    abstract fun toDto(defaultProduct: DefaultProduct): DefaultProductDto

    fun stocksToPrice(defaultProduct: DefaultProduct): Double {
        val storageId = defaultProduct.storage.id
        val stocks = defaultProduct.product.id?.let { stockRepository.findAllByProductId(it) }

        return stocks?.find { it.storage.id == storageId }?.salePrice ?: 0.0
    }
}
