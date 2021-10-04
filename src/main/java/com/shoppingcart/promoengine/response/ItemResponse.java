package com.shoppingcart.promoengine.response;

import lombok.Data;

@Data
public class ItemResponse {
    private String skuId;
    private Integer itemCount;
    private Long price;
}
