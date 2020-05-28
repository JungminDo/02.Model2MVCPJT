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
		
		System.out.println("������Ʈ Ʈ���ڵ�"+tranCode);
		
		PurchaseService purService = new PurchaseServiceImpl();
		purService.updateTranCode(purService.getPurchase(prodNo));
		
		Purchase purchase = purService.getPurchase(prodNo);
		System.out.println("������Ʈ Ʈ���ڵ� ��ǰ ��ȣ"+prodNo);
		request.setAttribute("purchase", purchase);	
		return "forward:/listPurchase.do?"; 	
	}
}
