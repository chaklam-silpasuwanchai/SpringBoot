package com.example.hibernate.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.hibernate.model.Address;
import com.example.hibernate.model.AddressId;
import com.example.hibernate.model.AnnualLeave;
import com.example.hibernate.model.Benefit;
import com.example.hibernate.model.Employee;
import com.example.hibernate.model.LeaveType;
import com.example.hibernate.model.Name;
import com.example.hibernate.model.SickLeave;
import com.example.hibernate.model.User;

@Service
public class TestService {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void testCache() throws InterruptedException {
		loadEntity(1);
		// after commit entity will become detached
	}

	@Transactional // will handle session automatically
	public void testFetch(int id) {
		System.out.println("-- Loading entities --");
		Employee employee = em.find(Employee.class, id);
		System.out.println("Employee loaded: " + employee.getName().getFname());

		// Address is lazy load, so will not be queried unless its info is needed
		System.out.println("-- Loading addresses --");
		System.out.println("City Address loaded: " + employee.getAddresses().iterator().next().getId().getCity());

		// Benefits are lazy load
		System.out.println("-- Loading benefits --");
		System.out.println("Benefits loaded: " + employee.getBenefits().iterator().next().getTitle());

		// User is lazy load
		System.out.println("-- Loading user --");
		System.out.println("User loaded: " + employee.getUser().getUsername());

	}

	@Transactional
	public void testCascadePersist(int id) {
		Employee employee = new Employee();
		Name name = new Name("Peter", "Shawn", "");
		employee.setName(name);
		employee.setAge(35);

		// add user
		User u = em.find(User.class, 3);
		employee.setUser(u);

		// add address
		Address add = new Address();
		AddressId addId = new AddressId();
		addId.setCity("Bangkok");
		addId.setHouseNo("33/9");
		addId.setStreetAddress("Fashion Island");
		addId.setZipcode("10304");
		add.setId(addId);
		add.setEmp(employee);
		List<Address> adds = new ArrayList<Address>();
		adds.add(add);
		employee.setAddresses(adds);

		// add benefits
		Set<Employee> employees = new HashSet<Employee>();
		employees.add(employee);
		Benefit benefit = new Benefit("Free Lunch", employees);
		Set<Benefit> benefits = new HashSet<Benefit>();
		benefits.add(benefit);
		employee.setBenefits(benefits);

		em.persist(employee); // this means that em will track all changes once trans finish

	}

	@Transactional
	public void testCascadeRemove(int id) {
		Employee employee = em.find(Employee.class, id);
		System.out.println("Employee loaded: " + employee.getName().getFname());
		em.remove(employee);

	}

	public void loadEntity(int id) {
		System.out.println("-- Loading entities --");
		Employee employee = em.find(Employee.class, id);
		System.out.println("Employee loaded: " + employee.getName().getFname());
	}

	@Transactional
	public void testCreateLeave(int id, LeaveType type) {
		LocalDate start = LocalDate.of(2018, 2, 13);
		LocalDate end = LocalDate.of(2018, 2, 15);
		Employee emp = em.find(Employee.class, id);
		switch (type) {
		case SICK:
			SickLeave sl = new SickLeave(emp, false, "Flu", start, end);
			em.persist(sl);
			break;
		case ANNUAL:
			AnnualLeave al = new AnnualLeave(emp, false, "Kids affairs", start, end);
			em.persist(al);
			break;

		}

	}

}
