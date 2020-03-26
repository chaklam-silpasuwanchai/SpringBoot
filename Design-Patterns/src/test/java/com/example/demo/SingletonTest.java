package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Singleton;
import com.example.demo.model.SpringSingleton;

import org.junit.jupiter.api.Assertions;

@SpringBootTest
class SingletonTest {
	
	@Autowired
	SpringSingleton springSing1;
	
	@Autowired
	SpringSingleton springSing2;
	
	@Test
	void contextLoads() {
		Singleton sing1 = Singleton.getInstance();
		Singleton sing2 = Singleton.getInstance();
		
		Assertions.assertSame(sing1, sing2);
		Assertions.assertSame(springSing1, springSing2);
	}

}
