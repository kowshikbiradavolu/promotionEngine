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
        log.info("Inside Final Cart Price method ");
        Long finalPrice = 0L;
        Item item = null;
        List<Promotion> promotionList = null;
        int quotient = 0;
        int remainder = 0;
        Promotion promotion = new Promotion();
        List<String> promoType = new ArrayList<>();
        Map<String, Integer> cartMap = new HashMap<>();
        List<ItemResponse> itemResponseList = new ArrayList<>();
        ItemResponse itemResponse = null;
        Long indvItemTotalPrice =0L;

        checkoutRequestList.stream().forEach(c -> cartMap.put(c.getSkuId(), c.getItemCount()));

        for (CheckoutRequest i : checkoutRequestList) {
            item = itemService.getItem(i.getSkuId());
            try {
                promotionList = promotionService.fetchPromoBySkuId(i.getSkuId());
                promoType = promotionList.stream().map(p -> p.getPromoType()).collect(Collectors.toList());
                if (promoType.size() > 0) {
                    if (promoType.contains("Fixed")) {
                        itemResponse = new ItemResponse();
                        promotion = promotionList.stream().filter(f -> f.getPromoType().equals("Fixed")).collect(Collectors.toList()).get(0);
                        quotient = cartMap.get(i.getSkuId()) / promotion.getPromoItemCount();
                        remainder = cartMap.get(i.getSkuId()) % promotion.getPromoItemCount();
                        indvItemTotalPrice = (quotient * promotion.getPromoPrice()) + (remainder * item.getPrice());
                        finalPrice = finalPrice + indvItemTotalPrice ;
                    } else if (promoType.contains("Percent")) {
                        itemResponse = new ItemResponse();
                        promotion = promotionList.stream().filter(f -> f.getPromoType().equals("Percent")).collect(Collectors.toList()).get(0);
                        indvItemTotalPrice = ((Double)((item.getPrice() * cartMap.get(i.getSkuId())) * ((100.00 - promotion.getPromoDiscountPercent()) / 100))).longValue();
                        finalPrice = finalPrice + indvItemTotalPrice;
                    } else if (promoType.contains("Combined")) {
                        itemResponse = new ItemResponse();
                        promotion = promotionList.stream().filter(f -> f.getPromoType().equals("Combined")).collect(Collectors.toList()).get(0);
                        Integer itemCount = cartMap.get(i.getSkuId());
                        Integer mappingItemCount = cartMap.get(promotion.getMappingSkuId());
                        if (null == mappingItemCount) {
                            indvItemTotalPrice =  item.getPrice() * cartMap.get(i.getSkuId());
                            finalPrice = finalPrice + indvItemTotalPrice;
                        } else {
                            if (itemCount >= mappingItemCount) {
                                indvItemTotalPrice =(mappingItemCount * promotion.getPromoPrice()) + ((itemCount - mappingItemCount) * item.getPrice());
                                finalPrice = finalPrice + indvItemTotalPrice;
                                cartMap.put(promotion.getMappingSkuId(), cartMap.get(promotion.getMappingSkuId()) - mappingItemCount);
                            } else {
                                indvItemTotalPrice = (mappingItemCount-itemCount) * promotion.getPromoPrice();
                                finalPrice = finalPrice + indvItemTotalPrice;
                                cartMap.put(promotion.getMappingSkuId(), cartMap.get(promotion.getMappingSkuId()) - itemCount);
                            }
                        }
                    }
                } else {
                    itemResponse = new ItemResponse();
                    indvItemTotalPrice=item.getPrice() * cartMap.get(i.getSkuId());
                    finalPrice = finalPrice + indvItemTotalPrice;
                }
                log.info(finalPrice + "--------" + i.getSkuId());
            }catch(Exception e){
                log.error("Exception while calculating final cart price");
                throw e;
            }
            itemResponse.setSkuId(i.getSkuId());
            itemResponse.setPrice(indvItemTotalPrice);
            itemResponse.setItemCount(i.getItemCount());
            itemResponseList.add(itemResponse);
        }
        CheckoutResponse checkoutResponse = new CheckoutResponse();
        checkoutResponse.setFinalCheckoutPrice(finalPrice);
        checkoutResponse.setItemList(itemResponseList);
        return checkoutResponse;
    }
}
