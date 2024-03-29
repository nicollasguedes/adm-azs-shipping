package com.me.nicollas.admazsshipping.entity;

import com.me.nicollas.admazsshipping.dto.request.ConsignorRequestDTO;
import com.me.nicollas.admazsshipping.enums.IdNumberTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "consignor")
public class Consignor {

    public Consignor(ConsignorRequestDTO requestDTO) {
        this.name = requestDTO.getName();
        this.email = requestDTO.getEmail();
        this.identificationNumberType = requestDTO.getIdentificationNumberType();
        this.identificationNumber = requestDTO.getIdentificationNumber();
        this.phone = requestDTO.getPhone();
        this.address = Optional.ofNullable(requestDTO.getAddress())
                .map(Address::new)
                .orElse(null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    //Social Security Number Type Enumerated
    @Column(nullable = false, name = "id_number_type")
    @Enumerated(EnumType.STRING)
    private IdNumberTypeEnum identificationNumberType = IdNumberTypeEnum.CPF;

    @Column(nullable = false, unique = true, name = "id_number")
    private String identificationNumber;

    @Column(nullable = false, name = "active", columnDefinition = "boolean default true")
    private boolean active = true;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "consignor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Shipment> shipments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consignor_address_id")
    private Address address;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Consignor consignor = (Consignor) o;
        return getId() != null && Objects.equals(getId(), consignor.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
