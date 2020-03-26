package com.example.rr.service;

import java.time.LocalDate;

import javax.money.MonetaryAmount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rr.model.Contract;
import com.example.rr.model.Product;
import com.example.rr.repository.ContractJpaRepository;
import com.example.rr.repository.ProductJpaRepository;

@Service
public class RevenueRecognitionFacadeImpl implements RevenueRecognitionFacade {

	private RevenueRecognitionService recognitionService;
	private ContractJpaRepository contractRepository;
	private ProductJpaRepository productRepository;

	@Autowired
	public RevenueRecognitionFacadeImpl(RevenueRecognitionService recognitionService, 
			ContractJpaRepository contractRepository,
			ProductJpaRepository productRepository) {
		this.recognitionService = recognitionService;
		this.contractRepository = contractRepository;
		this.productRepository = productRepository;
	}

	@Override
	public MonetaryAmount recognizedRevenue(int contractId, LocalDate asOf) {
		return recognitionService.recognizedRevenue(contractId, asOf);
	}

	@Override
	public void calculateRevenueRecognitions(int contractId) {
		recognitionService.calculateRevenueRecognitions(contractId);
		
	}

	@Override
	public int insertContractInformation(int productId, MonetaryAmount contractPrice, 
			LocalDate dateSigned) {
		Product product = productRepository.getOne(productId);
		Contract contract = new Contract(product, contractPrice, dateSigned);
		contractRepository.save(contract);
		return contract.getId();
	}
	
	@Override
	public int insertProductInformation(String name, String type) {
		Product product = new Product(name, type);
		productRepository.save(product);
		return product.getId();
	}

}
