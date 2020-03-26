package com.example.rr.service;

import java.time.LocalDate;

import javax.money.MonetaryAmount;

public interface RevenueRecognitionService {

	MonetaryAmount recognizedRevenue(int contractId, LocalDate asOf);
	
	void calculateRevenueRecognitions(int contractId);

}
