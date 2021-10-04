package com.shoppingcart.promoengine.repository;

import com.shoppingcart.promoengine.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion,Integer> {
   List<Promotion> findBySkuId(String skuId);
}
