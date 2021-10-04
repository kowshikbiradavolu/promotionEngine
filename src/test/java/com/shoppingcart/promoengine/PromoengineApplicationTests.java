package com.shoppingcart.promoengine;

import com.shoppingcart.promoengine.entities.Item;
import com.shoppingcart.promoengine.entities.Promotion;
import com.shoppingcart.promoengine.request.CheckoutRequest;
import com.shoppingcart.promoengine.response.CheckoutResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class PromoengineApplicationTests {


		@Test
		@Order(1)
		void testCreateItem() {
		List<Item> itemList = new ArrayList<>();
		Item item = new Item();
		item.setSkuId("A");
		item.setPrice(50L);
		itemList.add(item);
		item = new Item();
		item.setSkuId("B");
		item.setPrice(30L);
		itemList.add(item);
		item = new Item();
		item.setSkuId("C");
		item.setPrice(20L);
		itemList.add(item);
		item = new Item();
		item.setSkuId("D");
		item.setPrice(15L);
		itemList.add(item);

		ResponseEntity<Item[]> postResponse = restTemplate().postForEntity(getRootUrl() + "/pe-items/item", itemList, Item[].class);
		Assert.notNull(postResponse, "Received Null Response");
		Assert.notNull(postResponse.getBody(), "Received Null in the Response Body");
	}

		@Test
		@Order(2)
		void testgetItem() {
		ResponseEntity<Item[]> postResponse = restTemplate().getForEntity(getRootUrl() + "/pe-items/item", Item[].class);
		Assert.notNull(postResponse, "Received Null Response");
		Assert.notNull(postResponse.getBody(), "Received Null in the Response Body");
	}

		@Test
		@Order(3)
		void testCreatePromotion() {
		List<Promotion> promotionList = new ArrayList<>();
		Promotion promotion = new Promotion();
		promotion.setSkuId("A");
		promotion.setPromoType("Fixed");
		promotion.setPromoItemCount(3);
		promotion.setPromoPrice(130L);
		promotionList.add(promotion);
		promotion=new Promotion();
		promotion.setSkuId("B");
		promotion.setPromoType("Fixed");
		promotion.setPromoItemCount(2);
		promotion.setPromoPrice(45L);
		promotionList.add(promotion);
		promotion=new Promotion();
		promotion.setSkuId("C");
		promotion.setPromoType("Combined");
		promotion.setMappingSkuId("D");
		promotion.setPromoPrice(30L);
		promotionList.add(promotion);

		ResponseEntity<Promotion[]> postResponse = restTemplate().postForEntity(getRootUrl() + "/pe-promotion/promotion", promotionList, Promotion[].class);
		Assert.notNull(postResponse, "Received Null Response");
		Assert.notNull(postResponse.getBody(), "Received Null in the Response Body");
	}

		@Test
		@Order(4)
		void testFinalCartPrice(){

		List<CheckoutRequest> checkoutRequestList = new ArrayList<>();
		CheckoutRequest checkoutRequest = new CheckoutRequest();
		checkoutRequest.setSkuId("A");
		checkoutRequest.setItemCount(5);
		checkoutRequestList.add(checkoutRequest);
		checkoutRequest = new CheckoutRequest();
		checkoutRequest.setSkuId("B");
		checkoutRequest.setItemCount(5);
		checkoutRequestList.add(checkoutRequest);
		checkoutRequest = new CheckoutRequest();
		checkoutRequest.setSkuId("C");
		checkoutRequest.setItemCount(1);
		checkoutRequestList.add(checkoutRequest);
		checkoutRequest = new CheckoutRequest();
		checkoutRequest.setSkuId("D");
		checkoutRequest.setItemCount(1);
		checkoutRequestList.add(checkoutRequest);
		;
		ResponseEntity<CheckoutResponse> checkoutResponse = restTemplate().postForEntity(getRootUrl() + "/pe-checkout/finalCartPrice", checkoutRequestList, CheckoutResponse.class);
		Assert.notNull(checkoutResponse, "Received Null Response");
		Assert.notNull(checkoutResponse.getBody(), "Received Null in the Response Body");
		log.info(checkoutResponse.getBody().getFinalCheckoutPrice() +"----Final Price");
		Assert.isTrue(checkoutResponse.getBody().getFinalCheckoutPrice() == 380, "Final Cart Logic is not working");

	}

		private String getRootUrl() {
		return "http://localhost:8080";
	}

		@Bean
		public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	}


