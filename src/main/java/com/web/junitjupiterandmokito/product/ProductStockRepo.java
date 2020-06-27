package com.web.junitjupiterandmokito.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepo extends JpaRepository<ProductStock, Long>{
	
	Optional<ProductStock> findBySerialNo(String serialNo);
	Optional<ProductStock> findByWarrantyCardNo(String warrantyCardNo);

}
