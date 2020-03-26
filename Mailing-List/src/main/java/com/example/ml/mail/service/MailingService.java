package com.example.ml.mail.service;

import java.security.Principal;
import java.util.List;

import com.example.ml.model.MailingList;

public interface MailingService {
	List<MailingList> findUnsubscribed(Principal principal);
	List<MailingList> findSubscribed(Principal principal);
	void unsubscribe(int lid, Principal principal);
	void subscribe(int lid, Principal principal);
	void broadcast(int lid, String message);
}
