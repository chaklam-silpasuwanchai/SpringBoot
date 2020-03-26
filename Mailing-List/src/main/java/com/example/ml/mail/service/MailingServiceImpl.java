package com.example.ml.mail.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.ml.dao.MaillingListJPADao;
import com.example.ml.dao.UserJPADao;
import com.example.ml.model.AccessType;
import com.example.ml.model.MailingList;
import com.example.ml.model.User;

@Service
public class MailingServiceImpl implements MailingService {
	
	@Autowired
	MaillingListJPADao mailDao;

	@Autowired
	UserJPADao jpaDao;
	
	@Autowired
	private EmailService emailService; //Facade
	
	public MailingServiceImpl() {
		this.emailService = new EmailServiceImpl();
	}
	
	@Override
	public List<MailingList> findUnsubscribed(Principal principal) {
		User u = jpaDao.findByUsername(principal.getName());
		if (u.getRole().equalsIgnoreCase("ROLE_ADMIN"))
			return mailDao.findUnsubscribedAllList(u.getId());
		else
			return mailDao.findUnsubscribedPublicList(u.getId());
	}

	@Override
	public List<MailingList> findSubscribed(Principal principal) {
		User u = jpaDao.findByUsername(principal.getName());
		return mailDao.findSubscribedList(u.getId());
	}

	@Override
	public void unsubscribe(int lid, Principal principal) {
		User u = jpaDao.findByUsername(principal.getName());
		mailDao.unsubscribe(lid, u.getId());
		
	}

	@Override
	public void subscribe(int lid, Principal principal) {
		User u = jpaDao.findByUsername(principal.getName());
		MailingList mlist = mailDao.getOne(lid);

		if (mlist.getType() == AccessType.PRIVATE 
				&& !u.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
			//do nothing
		} else {
			mailDao.subscribe(lid, u.getId());
			
		}
		
	}

	@Override
	public void broadcast(int lid, String message) {
		MailingList ml = mailDao.findById(lid).orElse(new MailingList());
		List<User> u = jpaDao.findUsersByList(ml.getId());

		for (User user : u) {

			SimpleMailMessage emailMsg = new SimpleMailMessage();
			emailMsg.setTo(user.getEmail());
			emailMsg.setText(message);
			emailMsg.setSubject("New message from list: " + ml.getTitle());
			emailMsg.setFrom("admin@random.asia");

			try {
				emailService.sendEmail(emailMsg);
				System.out.println("successful");

			} catch (MailException ex) {
				// simply log it and go on...
				System.err.println(ex.getMessage());
			}

		}
		
	}

}
