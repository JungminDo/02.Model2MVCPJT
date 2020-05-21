package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


public interface PurchaseService {
	//��ǰ �߰�
	public void addPurchase(Purchase purchase) throws Exception;
	//��ǰ ����Ȯ��
	public Purchase getPurchase(int prodNo) throws Exception;
	//��ǰ����Ʈ
	public Map<String,Object> getPurchaseList(Search search) throws Exception;
	//��ǰ��������
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public void insertPurchase(Purchase purchase) throws Exception;
	
	
}