package com.delivery.testDelivery.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatResponse {
    private Integer quantity;
    private Double sum;
}
