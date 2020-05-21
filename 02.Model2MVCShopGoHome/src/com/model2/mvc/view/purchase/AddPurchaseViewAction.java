package com.model2.mvc.view.purchase;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddPurchaseViewAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int  prodNo = Integer.parseInt(request.getParameter("prodNo"));
        
    	ProductService service = new ProductServiceImpl();
    	System.out.println("ADD���ž׼Ǻ�"+prodNo);
		Product product = service.getProduct(prodNo);
    	request.setAttribute("product", product);
    	
		System.out.println("ADD���ž׼Ǻ�"+product);
        return "forward:/purchase/addPurchaseView.jsp";
    }
}