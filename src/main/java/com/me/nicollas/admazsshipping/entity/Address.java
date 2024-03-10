package com.me.nicollas.admazsshipping.entity;

import com.me.nicollas.admazsshipping.dto.request.AddressRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "address")
public class Address {

    public Address(AddressRequestDTO requestDTO) {
        this.zipCode = requestDTO.getZipCode();
        this.streetAddress = requestDTO.getStreetAddress();
        this.streetNumber = requestDTO.getStreetNumber();
        this.country = requestDTO.getCountry();
        this.city = requestDTO.getCity();
        this.state = requestDTO.getState();
        this.unitNumber = requestDTO.getUnitNumber();
        this.landmark = requestDTO.getLandmark();
        this.latitude = requestDTO.getLatitude();
        this.longitude = requestDTO.getLongitude();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    private UUID id;

    @Column(nullable = false, name = "zip_code")
    private String zipCode;

    @Column(nullable = false, name = "street_address")
    private String streetAddress;

    @Column(nullable = false, name = "street_number")
    private String streetNumber;

    @Column(nullable = false, name = "country")
    private String country;

    @Column(nullable = false, name = "city")
    private String city;

    @Column(nullable = false, name = "state")
    private String state;

    @Column(name = "unit_number")
    private String unitNumber;

    private String landmark;

    @Column
    private String latitude = "0";

    @Column
    private String longitude = "0";

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Address address = (Address) o;
        return getId() != null && Objects.equals(getId(), address.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
