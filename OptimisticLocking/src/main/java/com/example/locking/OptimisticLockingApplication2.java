package com.example.locking;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.locking.service.ProductService;

@SpringBootApplication
public class OptimisticLockingApplication2 {
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ConfigurableApplicationContext applicationContext = 
				SpringApplication.run(OptimisticLockingApplication2.class, args);
		ProductService ps = applicationContext.getBean(ProductService.class);
		
		ps.createMockProduct();
		ps.writeLockTransaction();
		ps.readLockTransaction();
   
	}
	
	

}
