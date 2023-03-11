package org.pickwicksoft.avizage.service.mapper

import org.pickwicksoft.avizage.domain.entity.Product
import org.pickwicksoft.avizage.domain.model.StockEntry
import org.pickwicksoft.avizage.repository.CategoryRepository
import org.pickwicksoft.avizage.repository.UnitRepository
import org.springframework.stereotype.Service

@Service
class StockEntryMapper(
    val categoryRepository: CategoryRepository,
    val unitRepository: UnitRepository
) {
    fun toProduct(entry: StockEntry) = Product(
        name = entry.name,
        iconName = entry.iconName,
        description = entry.description,
        unit = unitRepository.getReferenceById(entry.unitId),
        category = categoryRepository.getReferenceById(entry.categoryId.toLong()),
        barCode = entry.barCode
    )
}
