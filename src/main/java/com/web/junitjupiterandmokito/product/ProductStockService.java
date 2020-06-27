package com.web.junitjupiterandmokito.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.junitjupiterandmokito.business.exception.GenericException;

@Service
public class ProductStockService {
	
	@Autowired
	private ProductStockRepo productStockRepo;
	
	public void validateSerialNo(String serialNo) {
		
		if(productStockRepo.findBySerialNo(serialNo).isPresent()) {
			throw new GenericException("SerialNo already exists");
		}
		
	}
	
	public void validateWarrantyCardNo(String warrantyCardNo) {
		
		if(productStockRepo.findByWarrantyCardNo(warrantyCardNo).isPresent()) {
			throw new GenericException("WarrantyCardNo already exists");
		}
		
	}
	
	public ProductStock save(ProductStock entity) {
		
		validateSerialNo(entity.getSerialNo());
		
		if(entity.getWarrantyCardNo() != null) {
			validateWarrantyCardNo(entity.getWarrantyCardNo());
		}
		
		return productStockRepo.save(entity);
		
	}
	
}
