package com.model2.mvc.view.purchase;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.model2.mvc.framework.Action;

import com.model2.mvc.service.domain.Purchase;

import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class UpdateTranCodeAction extends Action {

	public UpdateTranCodeAction() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String tranCode = request.getParameter("tranCode");
		
		System.out.println("업데이트 트랜코드"+tranCode);
		
		PurchaseService purService = new PurchaseServiceImpl();
		purService.updateTranCode(purService.getPurchase(prodNo));
		
		Purchase purchase = purService.getPurchase(prodNo);
		System.out.println("업데이트 트랜코드 상품 번호"+prodNo);
		request.setAttribute("purchase", purchase);	
		return "forward:/listPurchase.do?"; 	
	}
}
