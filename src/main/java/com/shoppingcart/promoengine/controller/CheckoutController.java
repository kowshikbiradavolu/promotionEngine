package com.shoppingcart.promoengine.controller;

import com.shoppingcart.promoengine.request.CheckoutRequest;
import com.shoppingcart.promoengine.response.CheckoutResponse;
import com.shoppingcart.promoengine.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pe-checkout")
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;


    @PostMapping("/finalCartPrice")
    public ResponseEntity<CheckoutResponse> calFinalPrice(@RequestBody List<CheckoutRequest> checkoutRequestList){

        CheckoutResponse checkoutResponse = checkoutService.calFinalCartPrice(checkoutRequestList);;
        return ResponseEntity.status(HttpStatus.OK).body(checkoutResponse);

    }

}
