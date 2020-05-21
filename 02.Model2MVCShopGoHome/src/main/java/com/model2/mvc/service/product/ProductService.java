package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
<<<<<<< HEAD


public interface ProductService {
	
	public void addProduct(Product productVO) throws Exception;

	public Product getProduct(int prodNo) throws Exception;

	public Map<String,Object> getProductList(Search search) throws Exception;

	public void updateProduct(Product productVO) throws Exception;
=======
import com.model2.mvc.service.domain.Purchase;


public interface ProductService {
	//상품 추가
	public void addProduct(Product product) throws Exception;
	//상품 정보확인
	public Product getProduct(int prodNo) throws Exception;
	//상품리스트
	public Map<String,Object> getProductList(Search search) throws Exception;
	//상품정보수정
	public void updateProduct(Product product) throws Exception;
	
	public void insertProduct(Product product) throws Exception;
	
>>>>>>> refs/heads/new/dev
	
}