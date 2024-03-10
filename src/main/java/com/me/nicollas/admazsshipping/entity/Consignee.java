package com.me.nicollas.admazsshipping.entity;

import jakarta.persistence.*;

@Embeddable
public class Consignee {

    @Column(nullable = false, name = "consignee_name")
    private String consigneeName;

    @Column(nullable = false, name = "consignee_phone")
    private String consigneePhone;

    @Column(nullable = false, name = "consignee_email")
    private String consigneeEmail;

    @OneToOne
    @JoinColumn(name = "consignee_address_id")
    private Address consigneeAddress;
}
