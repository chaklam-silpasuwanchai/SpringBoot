package com.example.hibernate;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.hibernate.model.LeaveType;
import com.example.hibernate.service.TestService;

@SpringBootApplication
public class HibernateApplication {
	
	@Autowired
	static TestService ts;

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(HibernateApplication.class, args);

		TestService ts = applicationContext.getBean(TestService.class);

		//testing lazy fetch
		System.out.println("----Testing Fetch---");
		ts.testFetch(1);
		
		//testing cache
		System.out.println("----Testing Cache---");
	    TimeUnit.SECONDS.sleep(10); //make sure cache is cleared
		System.out.println("----Not loaded, require query---");
	    ts.testCache();
		System.out.println("----Already loaded---");
		ts.testCache();
		System.out.println("----Already loaded---");
		ts.testCache();
	    TimeUnit.SECONDS.sleep(10);
		System.out.println("----Not loaded, require query---");
	    ts.testCache();
	    
		//testing cascade persist
		System.out.println("----Testing Cascade Persist---");
		ts.testCascadePersist(1);
		System.out.println("----Try log in to H2 and try john with pwd of 1234.  See what has been persisted---");

		
		//testing cascade remove
		System.out.println("----Testing Cascade Remove---");
		ts.testCascadeRemove(1);
		System.out.println("----Try log in to H2 and try john with pwd of 1234.  See what has been deleted---");

		//testing inheritances
		System.out.println("----Testing Inheritances ---");
		System.out.println("----Adding Sick Leave for employee with emp_user_id 1---");
		ts.testCreateLeave(2, LeaveType.SICK);
		System.out.println("----Adding Annual Leave for employee with emp_user_id 1---");
		ts.testCreateLeave(2, LeaveType.ANNUAL);
		System.out.println("----Try log in to H2 and try john with pwd of 1234.  See what has been added in LEAVE table---");

	}



}
 