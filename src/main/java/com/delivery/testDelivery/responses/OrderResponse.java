package com.delivery.testDelivery.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private Integer quantity;
    private Integer price;
    private List<OrderMealsResponse> orderMealsResponses;

}
