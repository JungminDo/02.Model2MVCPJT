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
		int prod_no = Integer.parseInt(request.getParameter("prod_no"));
		String tran_code = request.getParameter("tran_code");
		
		System.out.println("업데이트 트랜코드"+tran_code);
		
		PurchaseService servicePur = new PurchaseServiceImpl();
		servicePur.updateTranCode(servicePur.getPurchase(prod_no));
		
		Purchase purchase = servicePur.getPurchase(prod_no);
		System.out.println("업데이트 트랜코드 상품 번호"+prod_no);
		request.setAttribute("purchase", purchase);	
		return "forward:/listPurchase.do?"; 	
	}
}
