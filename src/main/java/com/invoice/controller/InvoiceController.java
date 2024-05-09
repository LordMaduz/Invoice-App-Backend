package com.invoice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.invoice.exception.ApplicationError;
import com.invoice.model.InvoiceDTO;
import com.invoice.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
@CrossOrigin(origins = "*", allowCredentials = "false")
public record InvoiceController(InvoiceService invoiceService) {

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        try {
            return ResponseEntity.ok()
                .body(invoiceService.getAllInvoices());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> saveInvoice(@RequestBody InvoiceDTO invoice) {
        try {
            return ResponseEntity.ok()
                .body(invoiceService.saveInvoice(invoice));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> editInvoice(@RequestBody InvoiceDTO invoice, @PathVariable String id) {
        try {
            return ResponseEntity.ok()
                .body(invoiceService.updateInvoice(invoice, id));
        } catch (ApplicationError e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable String id) {
        try {
            return ResponseEntity.ok()
                .body(invoiceService.deleteInvoice(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/multi-delete/{ids}")
    public ResponseEntity<List<String>> deleteInvoices(@PathVariable String ids) {
        try {
            return ResponseEntity.ok()
                .body(invoiceService.deleteInvoice(Arrays.stream(ids.split(",")).toList()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
