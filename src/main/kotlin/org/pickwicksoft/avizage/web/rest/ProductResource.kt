package org.pickwicksoft.avizage.web.rest

import org.pickwicksoft.avizage.service.ProductService
import org.pickwicksoft.avizage.service.dto.CategoryDto
import org.pickwicksoft.avizage.service.dto.ProductDto
import org.pickwicksoft.avizage.service.mapper.CategoryMapper
import org.pickwicksoft.avizage.service.mapper.ProductMapper
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/product")
class ProductResource(
    private val productService: ProductService,
    private val productMapper: ProductMapper,
    private val categoryMapper: CategoryMapper
) {
    internal class ProductResourceException(message: String) : RuntimeException(message)

    private val log = LoggerFactory.getLogger(javaClass)
    @GetMapping("/{identifier}")
    fun getProducts(@PathVariable identifier: Long): ProductDto {
        log.debug("REST request to get default products")
        return productService.getProductByIdentifier(identifier)
            .map(productMapper::toDto)
            .orElseThrow { ProductResourceException("Product could not be found") }
    }

    @GetMapping("/product/categories")
    fun getCategories(): List<CategoryDto> {
        log.debug("REST request to get default categories")
        return productService.getCategories().map(categoryMapper::toDto)
    }
}
