package com.fiap.fastfood.common.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseProductResponse {

    String name;
    BigDecimal price;
    String description;
    String type; //TODO: AJUSTAR GATEWAY

}
