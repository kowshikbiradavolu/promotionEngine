package com.shoppingcart.promoengine.request;

import lombok.Data;

@Data
public class CheckoutRequest {

    private String skuId;
    private Integer itemCount;
}
