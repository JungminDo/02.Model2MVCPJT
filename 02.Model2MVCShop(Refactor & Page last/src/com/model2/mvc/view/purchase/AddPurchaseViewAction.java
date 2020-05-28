package com.model2.mvc.view.purchase;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
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
    	System.out.println("ADD구매액션뷰 상품번호"+prodNo);
		Product product = service.getProduct(prodNo);
    	request.setAttribute("product", product);
    	
    	HttpSession session=request.getSession(true);	
		User user = (User)session.getAttribute("user");
			
		request.setAttribute("user", user);
    	
		System.out.println("ADD구매액션뷰"+product);
        return "forward:/purchase/addPurchaseView.jsp";
    }
}