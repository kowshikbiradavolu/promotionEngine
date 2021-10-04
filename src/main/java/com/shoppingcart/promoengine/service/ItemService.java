package com.shoppingcart.promoengine.service;

import com.shoppingcart.promoengine.entities.Item;
import com.shoppingcart.promoengine.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<Item>();
         itemRepository.findAll().forEach(items1 -> items.add(items1));
        return items;
    }

    public Item getItem(String skuid) {
        return itemRepository.findById(skuid).get();
    }

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> saveItemsList(List<Item> itemsList){
       return itemRepository.saveAll(itemsList);

    }
    public void deleteItems() {
        itemRepository.deleteAll();
    }
}
