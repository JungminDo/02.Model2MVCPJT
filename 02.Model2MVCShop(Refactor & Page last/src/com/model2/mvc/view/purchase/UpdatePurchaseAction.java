package com.model2.mvc.view.purchase;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
		PurchaseService purService = new PurchaseServiceImpl();
		
		Purchase purchase = purService.getPurchase(tranNo);	
		UserService userService = new UserServiceImpl();
		
	
		
		purchase.setBuyer(userService.getUser(request.getParameter("buyerId")));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("divyDate"));
		
		
		
		System.out.println(purchase+" UpdatePurchase");
		purService.updatePurchase(purchase);
		
		
		System.out.println(purchase+" UpdatePurchaseAction22");
		
		
		request.setAttribute("purchase", purchase);
		
		return "forward:/purchase/updatePurchase.jsp";
	}
}
