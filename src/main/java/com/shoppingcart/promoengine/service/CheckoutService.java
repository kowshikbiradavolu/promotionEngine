package com.shoppingcart.promoengine.service;

import com.shoppingcart.promoengine.entities.Item;
import com.shoppingcart.promoengine.entities.Promotion;
import com.shoppingcart.promoengine.request.CheckoutRequest;
import com.shoppingcart.promoengine.response.CheckoutResponse;
import com.shoppingcart.promoengine.response.ItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CheckoutService {

    @Autowired
    ItemService itemService;

    @Autowired
    PromotionService promotionService;

    public CheckoutResponse calFinalCartPrice(List<CheckoutRequest> checkoutRequestList) {
       CheckoutResponse checkoutResponse = new CheckoutResponse();

        return checkoutResponse;
    }
}
