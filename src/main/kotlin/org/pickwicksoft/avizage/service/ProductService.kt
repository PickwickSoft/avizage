package org.pickwicksoft.avizage.service

import org.pickwicksoft.avizage.domain.entity.Category
import org.pickwicksoft.avizage.domain.entity.Product
import org.pickwicksoft.avizage.repository.CategoryRepository
import org.pickwicksoft.avizage.repository.ProductRepository
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
}

fun Long.length() = when (this) {
    0L -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}
