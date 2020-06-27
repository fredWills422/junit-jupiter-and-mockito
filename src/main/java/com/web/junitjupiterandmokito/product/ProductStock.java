package com.web.junitjupiterandmokito.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table
@Data
public class ProductStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name= "product_id")
	private Long productId;
	
	@NotNull
	@Column(name= "serial_no")
	private String serialNo;
	
	@Column(name = "warranty_card_no")
	private String warrantyCardNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getWarrantyCardNo() {
		return warrantyCardNo;
	}

	public void setWarrantyCardNo(String warrantyCardNo) {
		this.warrantyCardNo = warrantyCardNo;
	}
	
	
	
}
