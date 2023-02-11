package org.pickwicksoft.avizage.service

import org.pickwicksoft.avizage.domain.entity.Stock
import org.pickwicksoft.avizage.domain.entity.Storage
import org.pickwicksoft.avizage.repository.StockRepository
import org.pickwicksoft.avizage.repository.StorageRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class StockService(
    private val stockRepository: StockRepository,
    private val storageRepository: StorageRepository
) {
    fun getAllStorages(): List<Storage> {
        return storageRepository.findAll()
    }

    fun getProductStockForStorage(productId: Long, storageId: Int): Optional<Stock> {
        val stock = try {
            stockRepository.findAllByProductId(productId).first { stock -> stock.storage.id == storageId }
        } catch (e: NoSuchElementException) {
            null
        }
        return Optional.ofNullable(stock)
    }
}
