package com.model2.mvc.view.purchase;

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

public class AddPurchaseAction extends Action{
	
	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
	
		
		System.out.println("애드펄체이스액션");
		Purchase purchase = new Purchase();
		
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		ProductService proservice=new ProductServiceImpl();
		Product product = proservice.getProduct(prodNo);
		System.out.println(product+"vo GetProductAction");
		request.setAttribute("product", product);
		
		HttpSession session=request.getSession(true);	
		User user = (User)session.getAttribute("user");
		
        purchase.setPurchaseProd(product);
    	purchase.setBuyer(user);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setTranCode("1");
		
		System.out.println("set 상품" +purchase);
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchase);
		
		purchase = service.getPurchase(prodNo);
		
		System.out.println("insert 상품" +purchase);
		
		//service.updateTranCode(service.getPurchase(prodNo));
		
		purchase = service.getPurchase(prodNo);
		
		System.out.println("tran상품" +purchase);
		
		
		//System.out.println("PurchaseAction_Tran_No:  "+purchase.getTranNo());
		//session.setAttribute("purchase1", purchase);
		request.setAttribute("purchase1", purchase);
		return "forward:/purchase/addPurchase.jsp";
		
	}

}