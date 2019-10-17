package com.delivery.testDelivery.models.entities;

import com.delivery.testDelivery.models.audits.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_orders",
        initialValue = 1,
        allocationSize = 1
)
public class Order extends AuditModel {
    @Column(name = "overall_quantity")
    @NotNull(message = "overallQuantity is required")
    private Integer overallQuantity;

    @Column(name = "overall_price")
    @NotNull(message = "overallPrice is required")
    private Integer overallPrice;

    @ManyToOne
    @NotNull(message = "User is required")
    private User user;

    @Column(name = "payment_type")
    @NotNull(message = "paymentType is required")
    private String paymentType;


}
