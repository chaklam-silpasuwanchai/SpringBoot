package com.example.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ShoppingController {
	
	@Autowired
	private RestTemplate template;
	
	@GetMapping("/pay/{price}")
	public String invokePaymentService(@PathVariable int price) {
		
		// service-url/controller-url/method-url
		// traditional way
		//String url = "http://localhost:9090/payment-service/payNow/" + price;
		
		// eureka way: address and port will be taken by our service discovery
		String url = "http://PAYMENT-SERVICE/payment-service/payNow/" + price;
		return template.getForObject(url, String.class);
	}
}
