package org.pickwicksoft.avizage.service

import org.pickwicksoft.avizage.domain.entity.Category
import org.pickwicksoft.avizage.domain.entity.Product
import org.pickwicksoft.avizage.repository.DefaultCategoryRepository
import org.pickwicksoft.avizage.repository.DefaultProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DefaultDataService(
    private val defaultProductRepository: DefaultProductRepository,
    private val defaultCategoryRepository: DefaultCategoryRepository,
) {

    fun getDefaultProducts(): List<Product> {
        return defaultProductRepository.findAll().map { product ->
            product.product
        }
    }

    fun getDefaultCategories(): List<Category> {
        return defaultCategoryRepository.findAll().map { category ->
            category.category
        }
    }
}
