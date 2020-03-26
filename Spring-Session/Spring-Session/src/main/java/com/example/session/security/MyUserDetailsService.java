package com.example.session.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.session.model.User;
import com.example.session.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws 
	UsernameNotFoundException {
		
		//The service simply find the user by talking to dao, and 
		//pass that user to UserDetailsImpl
		User user = userDao.findByUsername(username);

		if(user==null)
			throw new UsernameNotFoundException("User 404");
		
		return new UserDetailsImpl(user);
	}

}
