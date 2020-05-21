package com.model2.mvc.service.purchase;


import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


public interface PurchaseDao {

	public void addPurchase (Purchase purchase) throws Exception;

	public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception ;
	
	public void insertPurchase(Purchase product) throws Exception;

	public Purchase getPurchase(int prodNo) throws Exception;
	
	public void updatePurchase(Purchase product) throws Exception;

	//�Խ��� Page ó���� ���� ��ü Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception;

	// �Խ��� currentPage Row �� return
	public String makeCurrentPageSql(String sql , Search search);
		
}
