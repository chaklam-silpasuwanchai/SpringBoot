package com.example.demo.model;

import java.beans.JavaBean;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserSpringPrototype {
	private List<String> userList;
	
	public UserSpringPrototype(){
		userList = new ArrayList<String>();
	}
	
	public UserSpringPrototype(List<String> list){
		this.userList=list;
	}
	public void loadData(){
		//read all employees from database and put into the list
		userList.add("Pankaj");
		userList.add("Raj");
		userList.add("David");
		userList.add("Lisa");
	}
	
	public List<String> getUserList() {
		return userList;
	}

}
