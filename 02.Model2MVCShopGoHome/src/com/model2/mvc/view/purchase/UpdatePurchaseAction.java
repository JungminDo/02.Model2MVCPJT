package com.model2.mvc.view.purchase;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class UpdatePurchaseAction extends Action {

	public UpdatePurchaseAction() {
		
	}
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		PurchaseService purService = new PurchaseServiceImpl();
		
		Purchase purchsae = purService.getPurchase(tranNo);
		
		System.out.println(purchsae+"vo GetPurchaseAction");
		
		UserService userService = new UserServiceImpl();
		
		purchsae.setBuyer(userService.getUser(request.getParameter("buyerId")));
		purchsae.setPaymentOption(request.getParameter("paymentOption"));
		purchsae.setReceiverName(request.getParameter("receiverName"));
		purchsae.setReceiverPhone(request.getParameter("receiverPhone"));
		purchsae.setDivyAddr(request.getParameter("receiverAddr"));
		purchsae.setDivyRequest(request.getParameter("receiverRequest"));
		purchsae.setDivyDate(request.getParameter("divyDate"));
		
		
		
		System.out.println(purchsae+" UpdatePurchase");
		purService.updatePurchase(purchsae);
		
		
		System.out.println(purchsae+" UpdatePurchaseAction22");
		
		request.setAttribute("purchase", purchsae);
		
		return "forward:/purchase/updatePurchase.jsp";
	}
}
