package com.shoppingcart.promoengine.repository;

import com.shoppingcart.promoengine.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item,String> {
}
