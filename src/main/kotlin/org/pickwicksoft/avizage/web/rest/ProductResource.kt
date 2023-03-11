package org.pickwicksoft.avizage.web.rest

import org.pickwicksoft.avizage.service.ProductService
import org.pickwicksoft.avizage.service.dto.CategoryDto
import org.pickwicksoft.avizage.service.dto.ProductDto
import org.pickwicksoft.avizage.service.mapper.CategoryMapper
import org.pickwicksoft.avizage.service.mapper.ProductMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.jhipster.web.util.HeaderUtil
import java.net.URI

@RestController
@RequestMapping("/api/product")
class ProductResource(
    private val productService: ProductService,
    private val productMapper: ProductMapper,
    private val categoryMapper: CategoryMapper
) {
    internal class ProductResourceException(message: String) : RuntimeException(message)

    private val log = LoggerFactory.getLogger(javaClass)

    @Value("\${jhipster.clientApp.name}")
    private val applicationName: String? = null

    private val ENTITY_NAME_CATEGORY = "Book"

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

    @PutMapping("/product/categories/{id}")
    fun updateCategory(@PathVariable id: Long, @RequestBody categoryDto: CategoryDto): ResponseEntity<CategoryDto> {
        log.debug("REST request to update category : {}", categoryDto)
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME_CATEGORY, categoryDto.id.toString()))
            .build()
    }

    @PostMapping("/product/categories")
    fun createCategory(@RequestBody categoryDto: CategoryDto): ResponseEntity<CategoryDto> {
        log.debug("REST request to save category : {}", categoryDto)
        return ResponseEntity
            .created(URI("/api/product/product/categories/${categoryDto.id}"))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME_CATEGORY, categoryDto.id.toString()))
            .body(categoryMapper.toDto(productService.create(categoryMapper.toEntity(categoryDto))))
    }
}
