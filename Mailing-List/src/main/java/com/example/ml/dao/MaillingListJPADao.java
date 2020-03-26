package com.example.ml.dao;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.ml.model.MailingList;

public interface MaillingListJPADao extends JpaRepository<MailingList, Integer> {

	List<MailingList> findByType(String type);

	@Transactional //will set read-only to false
	@Modifying  //for insert, update or delete
	@Query(value = "DELETE FROM SUBSCRIBING_LIST WHERE lid=:lid AND uid=:uid", nativeQuery = true)
	void unsubscribe(int lid, int uid);

	@Transactional
	@Modifying
	@Query(value = "INSERT into SUBSCRIBING_LIST values (:lid, :uid)", nativeQuery = true)
	void subscribe(int lid, int uid);
	
	// find lists by user
	@Query(value = "SELECT * FROM MAILING_LIST JOIN SUBSCRIBING_LIST ON MAILING_LIST.id = SUBSCRIBING_LIST.lid WHERE SUBSCRIBING_LIST.uid = :uid", nativeQuery = true)
	List<MailingList> findSubscribedList(int uid);

	// find public list that user did not subscribe
	@Query(value = "SELECT * from MAILING_LIST where id not in (SELECT MAILING_LIST.id FROM MAILING_LIST JOIN SUBSCRIBING_LIST "
			+ "ON MAILING_LIST.id = SUBSCRIBING_LIST.lid WHERE SUBSCRIBING_LIST.uid = :uid) AND type = 0", nativeQuery = true)
	List<MailingList> findUnsubscribedPublicList(int uid);
	
	// find all list that user did not subscribe
	@Query(value = "SELECT * from MAILING_LIST where id not in (SELECT MAILING_LIST.id FROM MAILING_LIST JOIN SUBSCRIBING_LIST "
			+ "ON MAILING_LIST.id = SUBSCRIBING_LIST.lid WHERE SUBSCRIBING_LIST.uid = :uid)", nativeQuery = true)
	List<MailingList> findUnsubscribedAllList(int uid);

}
