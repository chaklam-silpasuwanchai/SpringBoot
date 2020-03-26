package com.example.demo.model;

public class Member implements Role {

private String name;
	
	public Member() {
		super();
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getRole() {
		return "Member";
	}

	@Override
	public void checkAccess() {
		System.out.println("Granted access level: Member");
		
	}

}
