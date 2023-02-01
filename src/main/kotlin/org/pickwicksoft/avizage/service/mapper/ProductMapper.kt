package org.pickwicksoft.avizage.service.mapper

import org.mapstruct.*
import org.pickwicksoft.avizage.domain.entity.Product
import org.pickwicksoft.avizage.repository.StockRepository
import org.pickwicksoft.avizage.service.dto.ProductDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
abstract class ProductMapper {

    @Autowired
    lateinit var stockRepository: StockRepository

    abstract fun toEntity(productDto: ProductDto): Product

    @Mapping(target = "price", expression = "java(stocksToPrice(stockRepository.findAllByProductId(product.getId())))")
    abstract fun toDto(product: Product): ProductDto

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(productDto: ProductDto, @MappingTarget product: Product): Product

    fun stocksToPrice(stocks: MutableSet<org.pickwicksoft.avizage.domain.entity.Stock>): Double {
        return stocks.find { it.id == 1 }?.salePrice ?: 0.0
    }
}
