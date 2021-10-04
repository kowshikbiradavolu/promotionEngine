package com.shoppingcart.promoengine.service;

import com.shoppingcart.promoengine.entities.Promotion;
import com.shoppingcart.promoengine.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionService {

    @Autowired
    PromotionRepository promotionRepository;

    public List<Promotion> getAllPromos() {
        List<Promotion> promos = new ArrayList<Promotion>();
        promotionRepository.findAll().forEach(promo1 -> promos.add(promo1));
        return promos;
    }

    public List<Promotion> savePromosList(List<Promotion> promotionList){
        return promotionRepository.saveAll(promotionList);
    }

    public  List<Promotion> fetchPromoBySkuId(String skuId){
        return promotionRepository.findBySkuId(skuId);
    }

}
