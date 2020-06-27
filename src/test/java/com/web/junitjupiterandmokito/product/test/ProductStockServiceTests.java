package com.web.junitjupiterandmokito.product.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.web.junitjupiterandmokito.business.exception.GenericException;
import com.web.junitjupiterandmokito.product.ProductStock;
import com.web.junitjupiterandmokito.product.ProductStockRepo;
import com.web.junitjupiterandmokito.product.ProductStockService;

@SpringBootTest
public class ProductStockServiceTests {
	
	private final String serialNo = "1234";
	private final String warrantyCardNo = "1234";
	private final String serialNoEmpty = "12345";
	private final String warrantyCardNoEmpty = "12345";
	
	@TestConfiguration
	static class ProductStockServiceImplTestContextConfiguration{
		
		@Bean
		public ProductStockService productStocService() {
			return new ProductStockService();
		}
		
	}
	
	@Autowired
	private ProductStockService productStockService;
	
	@MockBean
	private ProductStockRepo productStockRepo;
	
	@BeforeEach
	public void setup() {
		
		ProductStock ps = new ProductStock();
		ps.setId(1L);
		ps.setSerialNo(serialNo);
		ps.setWarrantyCardNo(warrantyCardNo);
		ps.setProductId(1L);
		
		Mockito.when(productStockRepo.findBySerialNo(serialNo)).thenReturn(Optional.of(ps));
		Mockito.when(productStockRepo.findByWarrantyCardNo(warrantyCardNo)).thenReturn(Optional.of(ps));
		Mockito.when(productStockRepo.findBySerialNo(serialNoEmpty)).thenReturn(Optional.of(ps));
		Mockito.when(productStockRepo.findByWarrantyCardNo(warrantyCardNoEmpty)).thenReturn(Optional.of(ps));
		
	}
	
	@Test
	public void whenSavingInvalidSerialNo_thenReturnException() {
		ProductStock ps = new ProductStock();
		ps.setSerialNo(serialNo);
		assertThatThrownBy(()-> productStockService.save(ps)).isInstanceOf(GenericException.class).hasMessageContaining("SerialNo");
	}
	
	@Test
	public void whenSavingInvalidWarrantyCardNo_thenReturnException() {
		ProductStock ps = new ProductStock();
		ps.setWarrantyCardNo(warrantyCardNo);
		assertThatThrownBy(()-> productStockService.save(ps)).isInstanceOf(GenericException.class).hasMessageContaining("WarrantyCardNo");
	}
	
	@Test
	public void whenSaving_thenOk(){
		
		ProductStock ps = new ProductStock();
		ps.setProductId(1L);
		ps.setSerialNo("serialNo");
		ps.setWarrantyCardNo("4949");
		ps.setId(1L);
		
		Mockito.when(productStockRepo.save(ps)).thenReturn(ps);
		
		assertThat(productStockService.save(ps).getId()).isEqualTo(1L);
		assertThat(productStockService.save(ps).getSerialNo()).isEqualTo("serialNo");
		
	}
	
}
