package com.shoppingcart.promoengine.controller;

import com.shoppingcart.promoengine.entities.Item;
import com.shoppingcart.promoengine.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pe-items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/item")
    private List<Item> getAllItems(){
    return itemService.getAllItems();
    }

    @GetMapping("/item/{skuid}")
    private Item getItem(@PathVariable String skuid){
    return itemService.getItem(skuid);
    }

    @PostMapping("/item")
    private List<Item> insertItem(@RequestBody List<Item> item){
       return itemService.saveItemsList(item);
    }

    @DeleteMapping("/item")
    private void deleteItems(){
        itemService.deleteItems();
    }

}
