package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


public interface PurchaseService {
	//상품 추가
	public void addPurchase(Purchase purchase) throws Exception;
	//상품 정보확인
	public Purchase getPurchase(int prodNo) throws Exception;
	//상품리스트
	public Map<String,Object> getPurchaseList(Search search) throws Exception;
	//상품정보수정
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public void insertPurchase(Purchase purchase) throws Exception;
	
	
}