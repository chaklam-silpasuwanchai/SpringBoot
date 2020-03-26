package com.example.rr.script;

import java.time.LocalDate;

import javax.money.MonetaryAmount;

public interface RevenueRecognitionScript {
	MonetaryAmount recognizedRevenue(int contractId, LocalDate asOf);
	
	void calculateRevenueRecognitions(int contractId);
	
	int insertContractInformation(
			int productId, MonetaryAmount contractPrice, LocalDate dateSigned);

	int insertProductInformation(String name, String type);
}
