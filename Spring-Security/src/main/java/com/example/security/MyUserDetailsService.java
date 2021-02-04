package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.dao.UserJPADao;
import com.example.security.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserJPADao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		//The service simply find the user by talking to dao
		User user = userDao.findByUsername(username);
		
		if(user==null)
			throw new UsernameNotFoundException("User 404");
		
		return new UserDetailsImpl(user);
	}

}
