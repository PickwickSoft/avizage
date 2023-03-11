package org.pickwicksoft.avizage.web.rest

import org.pickwicksoft.avizage.domain.entity.Product
import org.pickwicksoft.avizage.domain.model.StockEntry
import org.pickwicksoft.avizage.service.ProductService
import org.pickwicksoft.avizage.service.StockService
import org.pickwicksoft.avizage.service.dto.StockDto
import org.pickwicksoft.avizage.service.dto.StorageDto
import org.pickwicksoft.avizage.service.mapper.StockEntryMapper
import org.pickwicksoft.avizage.service.mapper.StockMapper
import org.pickwicksoft.avizage.service.mapper.StorageMapper
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/stocks")
class StockResource(
    private val stockService: StockService,
    private val storageMapper: StorageMapper,
    private val stockEntryMapper: StockEntryMapper,
    private val stockMapper: StockMapper,
    private val productService: ProductService
) {
    internal class StockResourceException(message: String) : RuntimeException(message)

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/storages")
    fun getAllStorages(): List<StorageDto> {
        log.debug("GET storages")
        val stores = stockService.getAllStorages()
        return stores.map(storageMapper::toDto)
    }

    @GetMapping("/{productId}/{storageId}")
    fun getProductStockForStorage(@PathVariable productId: Long, @PathVariable storageId: Int): StockDto {
        log.debug("GET product(id = $productId) for storage $storageId")
        return stockService.getProductStockForStorage(productId, storageId)
            .map(stockMapper::toDto)
            .orElseThrow { StockResourceException("Stock for product could not be found") }
    }

    @PostMapping("/{storageId}")
    fun addProductStockToStorage(@Valid @RequestBody stock: StockEntry, @PathVariable storageId: Int) {
        log.debug("POST product stock $stock to storage $storageId")
        stock.productId?.let { productService.getProductByIdentifier(it) }
            ?.let {
                var product: Product = if (it.isEmpty) {
                    productService.addProduct(stockEntryMapper.toProduct(stock))
                } else {
                    it.get()
                }
                stockService.addStock(storageId, product, stock)
            }
    }
}
