package com.shoppingcart.promoengine.controller;

import com.shoppingcart.promoengine.entities.Promotion;
import com.shoppingcart.promoengine.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pe-promotion")
public class PromotionController {

    @Autowired
    PromotionService promotionService;

    @GetMapping("/promotion")
    private ResponseEntity<List<Promotion>> getAllPromotions(){
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.getAllPromos());
    }

    @PostMapping("/promotion")
    private ResponseEntity<List<Promotion>> getAllPromotions(@RequestBody List<Promotion> promotionList){
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.savePromosList(promotionList));
    }

    @GetMapping("/promotion/{skuId}")
    private ResponseEntity<List<Promotion>> getPromoBySkuId(@PathVariable String skuId){
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.fetchPromoBySkuId(skuId));
    }


}
