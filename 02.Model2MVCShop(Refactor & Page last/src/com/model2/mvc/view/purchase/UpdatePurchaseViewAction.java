package com.model2.mvc.view.purchase;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdatePurchaseViewAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int  tranNo = Integer.parseInt(request.getParameter("tranNo"));
    	
    	System.out.println("UpDate备概咀记轰"+tranNo);
    	PurchaseService service = new PurchaseServiceImpl();
        Purchase purchase = service.getPurchase(tranNo);

        
        System.out.println("UpDate备概咀记轰"+purchase);
        request.setAttribute("purchase", purchase);
    
        return "forward:/purchase/updatePurchaseView.jsp";
    }
}