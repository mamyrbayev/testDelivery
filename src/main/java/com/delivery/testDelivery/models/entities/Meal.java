package com.delivery.testDelivery.models.entities;

import com.delivery.testDelivery.models.audits.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "title")
    @NotNull(message = "title is required")
    private String title;

    @Column(name = "price")
    @NotNull(message = "price is required")
    private Double price;

    @Column(name = "ingredients")
    @NotNull(message = "ingredients is required")
    private String ingredients;

    @Column(name = "description")
    private String description;

    @Column(name = "tag")
    private String tag;
//
//    @ManyToMany
//    private Rating rating;

    @Column(name = "url")
    private String url;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "category is required")
    private Category category;

}
