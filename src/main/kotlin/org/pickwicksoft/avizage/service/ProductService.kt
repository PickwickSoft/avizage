package org.pickwicksoft.avizage.service

import org.hibernate.id.IdentifierGenerator.ENTITY_NAME
import org.pickwicksoft.avizage.domain.entity.Category
import org.pickwicksoft.avizage.domain.entity.Product
import org.pickwicksoft.avizage.repository.CategoryRepository
import org.pickwicksoft.avizage.repository.ProductRepository
import org.pickwicksoft.avizage.web.rest.errors.BadRequestAlertException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.math.abs
import kotlin.math.log10

@Service
@Transactional
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
) {

    private val ENTITY_NAME_CATEGORY = "Category"
    private val ENTITY_NAME_Product = "Product"

    fun getProductByIdentifier(identifier: Long): Optional<Product> {
        return if (identifier.length() != 13) {
            productRepository.findById(identifier)
        } else {
            productRepository.findOneByBarCode(identifier)
        }
    }

    fun getCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    fun existsCategoryWithName(name: String): Boolean {
        return categoryRepository.existsByName(name)
    }

    fun update(category: Category, id: Long): Category {
        if (category.id == null) {
            throw BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull")
        }
        if (id != category.id) {
            throw BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid")
        }

        if (!categoryRepository.existsById(id)) {
            throw BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound")
        }
        return categoryRepository.save(category)
    }

    fun create(category: Category): Category {
        if (category.id !== null) {
            throw BadRequestAlertException(
                "Invalid id", ENTITY_NAME_CATEGORY, "idnull"
            )
        }
        if (existsCategoryWithName(category.name)) {
            throw BadRequestAlertException("Name already used", ENTITY_NAME_CATEGORY, "nameUsed")
        }
        return categoryRepository.save(category)
    }
}

fun Long.length() = when (this) {
    0L -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}
