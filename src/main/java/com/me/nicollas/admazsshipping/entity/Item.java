package com.me.nicollas.admazsshipping.entity;

import com.me.nicollas.admazsshipping.dto.request.ItemRequestDTO;
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
@Table(name = "item")
public class Item {

    public Item(ItemRequestDTO itemRequestDTO) {
        this.name = itemRequestDTO.getName();
        this.value = itemRequestDTO.getValue();
        this.amount = itemRequestDTO.getAmount();
        this.description = itemRequestDTO.getDescription();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    private UUID id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "value")
    private double value;

    @Column(nullable = false, name = "amount")
    private double amount;

    @Column(nullable = false, name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false, name = "shipment_id", referencedColumnName = "id")
    private Shipment shipment;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Item item = (Item) o;
        return getId() != null && Objects.equals(getId(), item.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
