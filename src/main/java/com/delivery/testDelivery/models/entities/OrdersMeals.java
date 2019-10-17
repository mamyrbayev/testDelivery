package com.delivery.testDelivery.models.entities;

import com.delivery.testDelivery.models.audits.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders_meals")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_orders_meals",
        initialValue = 1,
        allocationSize = 1
)
public class OrdersMeals extends AuditModel {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(foreignKey = @ForeignKey(name = "meal_id"))
    private Meal meal;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JsonIgnore
    private Order order;

    @Column(name = "quantity")
    @NotNull(message = "Quantity is required")
    private Integer quantity;


}
