package com.me.nicollas.admazsshipping.entity;

import com.me.nicollas.admazsshipping.dto.request.ShipmentStatusRequestDTO;
import com.me.nicollas.admazsshipping.enums.ShipmentStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "shipment_status_history")
public class ShipmentStatusHistory {

    public ShipmentStatusHistory(ShipmentStatusRequestDTO shipmentStatusRequestDTO) {
        this.status = shipmentStatusRequestDTO.getStatus();
        this.message = shipmentStatusRequestDTO.getMessage();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private ShipmentStatusEnum status = ShipmentStatusEnum.LABEL_CREATED;

    @Column(nullable = false, name = "message")
    private String message;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

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
        ShipmentStatusHistory that = (ShipmentStatusHistory) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
