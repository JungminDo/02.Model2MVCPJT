package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
<<<<<<< HEAD
<<<<<<< HEAD

import com.model2.mvc.service.domain.Purchase;



public interface PurchaseService {

	public void addPurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public Purchase getPurchase2(int prodNo) throws Exception;
	
	public Map<String, Object> getPurchaseList(Search search,  String buyerId) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;

	void updatePurchase(Purchase purchase) throws Exception;
=======
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
	
>>>>>>> refs/heads/new/dev
=======
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
	
>>>>>>> refs/heads/new/dev
	
}