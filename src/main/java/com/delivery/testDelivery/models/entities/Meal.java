package com.delivery.testDelivery.models.entities;

import com.delivery.testDelivery.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "meals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_meals",
        initialValue = 1,
        allocationSize = 1
)
public class Meal extends AuditModel {
    @Column(name = "name")
    @NotNull(message = "name is required")
    private String name;

    @Column(name = "price")
    @NotNull(message = "price is required")
    private Integer price;

    @Column(name = "ingredients")
    @NotNull(message = "ingredients is required")
    private String ingredients;

    @Column(name = "description")
    private String description;

    @Column(name = "tag")
    private String tag;

    @Column(name = "photo_path")
    private String photoPath;

    @ManyToOne
    @NotNull(message = "category is required")
    private Category category;

}
