package com.invoice.model.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String invoiceNumber;
    private double amount;
    private String email;
    private String invoiceDate;
    private String status;
    @Embedded
    private Customer customer;

    @Data
    @NoArgsConstructor
    public static class Customer{
        private String name;
        private String company;
        private String country;
    }

}
