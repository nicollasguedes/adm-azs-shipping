package com.me.nicollas.admazsshipping.entity;

import com.me.nicollas.admazsshipping.dto.request.ShipmentRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "shipment")
public class Shipment {

    public Shipment(ShipmentRequestDTO shipmentRequestDTO) {
        this.price = shipmentRequestDTO.getPrice();
        this.width = shipmentRequestDTO.getWidth();
        this.height = shipmentRequestDTO.getHeight();
        this.length = shipmentRequestDTO.getLength();
        this.weight = shipmentRequestDTO.getWeight();
        this.consignee = Optional.ofNullable(shipmentRequestDTO.getConsigneeRequest())
                .map(Consignee::new)
                .orElse(null);
        this.itemList = shipmentRequestDTO.getItemList().stream()
                .map(itemRequestDTO -> {
                    Item item = new Item(itemRequestDTO);
                    item.setShipment(this);
                    return item;
                })
                .collect(Collectors.toList());

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    private UUID id;

    private double price;

    private double width;

    private double height;

    private double length;

    private double weight;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Item> itemList = new ArrayList<>();

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<ShipmentStatusHistory> statusList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false, name = "consignor_id", referencedColumnName = "id")
    private Consignor consignor;

    @Embedded
    private Consignee consignee;

    public double getTotalVolume() {
        return width * height * length;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Shipment shipment = (Shipment) o;
        return getId() != null && Objects.equals(getId(), shipment.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
