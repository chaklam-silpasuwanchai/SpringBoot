package com.example.ml.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ml.mail.service.MailingService;
import com.example.ml.mail.service.MailingServiceImpl;
import com.example.ml.model.MailingList;

@Controller
public class MaillingListController {

	@Autowired
	private MailingService mailingService; //Facade

	public MaillingListController() {
		this.mailingService = new MailingServiceImpl();
	}

	// find list that user not subscribe
	@RequestMapping(path = "/findUnsubscribed", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<MailingList> findUnsubscribed(Principal principal) {
		return mailingService.findUnsubscribed(principal);
	}

	// find list by user
	@RequestMapping(path = "/findSubscribed", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<MailingList> findSubscribed(Principal principal) {
		return mailingService.findSubscribed(principal);
	}

	@RequestMapping(path = "/unsubscribe/{lid}", method = RequestMethod.GET)
	public String unsubscribe(@PathVariable("lid") int lid, Principal principal) {
		mailingService.unsubscribe(lid, principal);
		return "redirect:/home";
	}

	@RequestMapping(path = "/subscribe/{lid}", method = RequestMethod.GET)
	public String subscribe(@PathVariable("lid") int lid, Principal principal) {
		mailingService.subscribe(lid, principal);
		return "redirect:/home";
	}

	// Invite
	/* Send email and add to database */

	// simple broadcast message to showcase email service
	@RequestMapping(path = "/admin/broadcast/{lid}", method = RequestMethod.GET)
	public String broadCast(@PathVariable("lid") int lid) {
		
		String message = "some random message";
		mailingService.broadcast(lid, message);
		return "redirect:/home";

	}

}
