package com.me.nicollas.admazsshipping.entity;

import com.me.nicollas.admazsshipping.enums.IdNumberTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "client")
public class Consignor {

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

    @OneToOne
    @JoinColumn(name = "consignor_address_id")
    private Address consignorAddress;

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
