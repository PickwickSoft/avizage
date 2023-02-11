package org.pickwicksoft.avizage.service.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import org.pickwicksoft.avizage.domain.entity.Sale
import org.pickwicksoft.avizage.service.dto.SaleDto
import org.springframework.stereotype.Service

@Service
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
abstract class SaleMapper {
    @Mapping(target = "user", source = "user.login")
    @Mapping(target = "storage", source = "storage.name")
    @Mapping(target = "itemName", expression = "java(getItemName(sale))")
    abstract fun toDto(sale: Sale): SaleDto

    fun getItemName(sale: Sale): String = sale.product?.name ?: sale.category!!.name
}
