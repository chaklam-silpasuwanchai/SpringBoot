package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Singleton;
import com.example.demo.model.SpringSingleton;
import com.example.demo.model.UserPrototype;
import com.example.demo.model.UserSpringPrototype;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
class PrototypeTest {
	
	@Autowired
	UserSpringPrototype usp1;
	
	@Autowired
	UserSpringPrototype usp2;
	
	@Test
	void contextLoads() {
		UserPrototype up = new UserPrototype();
		up.loadData();
		
		try {
			UserPrototype up1 = (UserPrototype) up.clone();
			UserPrototype up2 = (UserPrototype) up.clone();
			Assertions.assertSame(up1, up2);

		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void contextLoads2() {
		usp1.loadData();
		usp2.loadData();
		Assertions.assertSame(usp1, usp2);		
	}

}
