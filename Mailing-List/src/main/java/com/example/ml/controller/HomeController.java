package com.example.ml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ml.dao.MaillingListJPADao;

@Controller
public class HomeController {
	
	@Autowired
	MaillingListJPADao mailDao;
	
	//can only access by user role
	@RequestMapping(path = "/home")
	public String userDashboard() {
		return "home.jsp";
	}
	
	@RequestMapping(path = "/login")
	public String loginPage() {
		return "login.jsp";
	}
	
	@RequestMapping(path = "/logout-success")
	public String logoutPage() {
		return "logout.jsp";
	}
	
	
}
