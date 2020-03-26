package com.example.hibernate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hibernate.model.User;
import com.example.hibernate.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//The service simply find the user by talking to dao, and pass that user to UserDetailsImpl
		User user = userRepo.findByUsername(username);

		if(user==null)
			throw new UsernameNotFoundException("User 404");
		
		return new UserDetailsImpl(user);
	}

}
