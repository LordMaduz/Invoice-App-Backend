package com.invoice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.invoice.exception.ApplicationError;
import com.invoice.mapper.InvoiceMapper;
import com.invoice.model.InvoiceDTO;
import com.invoice.model.entity.Invoice;
import com.invoice.repository.InvoiceRepository;

@Service
public record InvoiceService(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {

    public InvoiceDTO saveInvoice(final InvoiceDTO invoiceDTO) {
        invoiceDTO.setInvoiceNumber(generateUniqueId());
        final Invoice invoice = new Invoice();
        invoiceMapper.toEntity(invoiceDTO, invoice);
        return invoiceMapper.toDTO(invoiceRepository.save(invoice));
    }

    public InvoiceDTO updateInvoice(final InvoiceDTO invoiceDTO, final String id) {
        Optional<Invoice> optional = invoiceRepository.findById(id);
        if (optional.isPresent()) {
            final Invoice invoice = optional.get();
            invoiceMapper.toEntity(invoiceDTO, invoice);
            return invoiceMapper.toDTO(invoiceRepository.save(invoice));
        } else {
            throw new ApplicationError("No Invoice with given ID");
        }
    }

    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll()
            .stream()
            .map(invoiceMapper::toDTO)
            .toList();
    }

    public String deleteInvoice(final String id) {
        invoiceRepository.deleteById(id);
        return id;
    }

    public List<String> deleteInvoice(final List<String> ids) {
        invoiceRepository.deleteAllById(ids);
        return ids;
    }

    private String generateUniqueId(){
        return UUID.randomUUID().toString().substring(0,8);
    }
}
