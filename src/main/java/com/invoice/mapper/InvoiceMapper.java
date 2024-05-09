package com.invoice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.invoice.model.InvoiceDTO;
import com.invoice.model.entity.Invoice;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface InvoiceMapper {

    void toEntity(InvoiceDTO invoiceDTO, @MappingTarget Invoice invoice);
    InvoiceDTO toDTO(Invoice invoice);
}
