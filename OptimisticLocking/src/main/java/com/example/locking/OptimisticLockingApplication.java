package com.example.locking;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.locking.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class OptimisticLockingApplication {
	
	static HttpClient client = HttpClient.newHttpClient();
	static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(OptimisticLockingApplication.class, args);
		
        //POST
		insert("Microsoft Powerpppointt", 499);
        
		//GET
		String jsonPowerPoint = get(1);
		Product pro = mapper.readValue(jsonPowerPoint, Product.class);
		pro.setName("Powerpppoint");
		
		//GET
		String jsonPowerPoint2 = get(1);
		Product pro2 = mapper.readValue(jsonPowerPoint2, Product.class);
		pro2.setName("Powerpoint");
		
		//PUT (the version will increment automatically)
		update(convertObjToJSON(pro));
		
		//PUT (this will raise an error because the version is different)
		update(convertObjToJSON(pro2));
   
	}
	
	private static String get(int id) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/products/" + id))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") //optional line
                .build();
		System.out.println("Get request: " + sendRequest(request).toString());
		return sendRequest(request);
	}
	
	private static void insert(String name, int price) throws 
	IOException, InterruptedException {
		// json formatted data
        String json = makeJSON(name, price);
        
        HttpRequest request = HttpRequest.newBuilder()
        		.POST(HttpRequest.BodyPublishers.ofString(json)) //.POST accepts a BodyPublisher
        		.uri(URI.create("http://localhost:8080/products"))
        		.setHeader("User-Agent", "Java 11 HttpClient Bot")
        		.header("Content-Type", "application/json")
        		.build();
        
        System.out.println("Inserted: " + sendRequest(request));
        
	}
	
	private static void update(String jsonString) throws IOException, 
	InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
        		.PUT(HttpRequest.BodyPublishers.ofString(jsonString))
        		.uri(URI.create("http://localhost:8080/products/"))
        		.setHeader("User-Agent", "Java 11 HttpClient Bot")
        		.header("Content-Type", "application/json")
        		.build();
        System.out.println("Put request: " + sendRequest(request).toString());
       
	}
	
	private static String makeJSON(String name, int price) {
		String json = new StringBuilder()
                .append("{")
                .append("\"name\":\"" + name + "\",")
                .append("\"price\":\"" + price + "\"")
                .append("}").toString();
		return json;
	}
	
	private static String sendRequest(HttpRequest request) throws IOException, 
	InterruptedException{
		HttpResponse<String> response = 
        		client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
	}
	
	private static String convertObjToJSON(Product product) throws 
	JsonProcessingException {
		String jsonString = mapper.writeValueAsString(product);
		return jsonString;
	}

}
