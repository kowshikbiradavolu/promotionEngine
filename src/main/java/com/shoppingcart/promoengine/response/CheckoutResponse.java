package com.shoppingcart.promoengine.response;

import lombok.Data;

import java.util.List;

@Data
public class CheckoutResponse {

    private List<ItemResponse> itemList;
    private Long finalCheckoutPrice;

}
