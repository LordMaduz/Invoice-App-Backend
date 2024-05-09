package com.invoice.model;

import lombok.Data;

@Data
public class InvoiceDTO {

    private String id;
    private String invoiceNumber;
    private Double amount;
    private String email;
    private String invoiceDate;
    private String status;
    private CustomerDTO customer;

    @Data
    public static class CustomerDTO {

        private String name;
        private String company;
        private String country;
    }
}
