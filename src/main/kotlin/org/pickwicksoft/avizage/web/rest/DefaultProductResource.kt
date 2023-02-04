package org.pickwicksoft.avizage.web.rest

import org.pickwicksoft.avizage.service.DefaultDataService
import org.pickwicksoft.avizage.service.dto.CategoryDto
import org.pickwicksoft.avizage.service.dto.DefaultProductDto
import org.pickwicksoft.avizage.service.mapper.CategoryMapper
import org.pickwicksoft.avizage.service.mapper.DefaultProductMapper
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/shop")
class DefaultProductResource(
    private val defaultDataService: DefaultDataService,
    private val productMapper: DefaultProductMapper,
    private val categoryMapper: CategoryMapper
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/product/default")
    fun getDefaultProducts(): List<DefaultProductDto> {
        log.debug("REST request to get default products")
        return defaultDataService.getDefaultProducts().map(productMapper::toDto)
    }

    @GetMapping("/product/category/default")
    fun getDefaultCategories(): List<CategoryDto> {
        log.debug("REST request to get default categories")
        return defaultDataService.getDefaultCategories().map(categoryMapper::toDto)
    }
}
