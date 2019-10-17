package com.delivery.testDelivery.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMealsResponse {
    private Long id;
    private Integer quantity;
    private Long orderId;
    private Long mealId;
}
