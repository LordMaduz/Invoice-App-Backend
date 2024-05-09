package com.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.model.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

}
