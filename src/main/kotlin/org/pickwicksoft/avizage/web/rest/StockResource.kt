package org.pickwicksoft.avizage.web.rest

import org.pickwicksoft.avizage.service.StockService
import org.pickwicksoft.avizage.service.dto.StockDto
import org.pickwicksoft.avizage.service.dto.StorageDto
import org.pickwicksoft.avizage.service.mapper.StockMapper
import org.pickwicksoft.avizage.service.mapper.StorageMapper
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stock")
class StockResource(
    private val stockService: StockService,
    private val storageMapper: StorageMapper,
    private val stockMapper: StockMapper
) {
    internal class StockResourceException(message: String) : RuntimeException(message)

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/storages")
    fun getAllStorages(): List<StorageDto> {
        val stores = stockService.getAllStorages()
        return stores.map(storageMapper::toDto)
    }

    @GetMapping("/{productId}/{storageId}")
    fun getProductStockForStorage(@PathVariable productId: Long, @PathVariable storageId: Int): StockDto {
        return stockService.getProductStockForStorage(productId, storageId)
            .map(stockMapper::toDto)
            .orElseThrow { StockResourceException("Stock for product could not be found") }
    }
}
