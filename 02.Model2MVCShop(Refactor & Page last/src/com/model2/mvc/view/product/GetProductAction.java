package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("°ÙÇÁ·Î´öÆ®¾×¼Ç");
		
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		System.out.println(prodNo);
		
		ProductService service=new ProductServiceImpl();
		
		Product product = service.getProduct(prodNo);
		
		System.out.println(product+"product GetProductAction");
		
		request.setAttribute("product", product);
		
		System.out.println("¿©±â VO °ÙÇÁ·Î´öÆ®¾×¼Ç");
		

		request.setAttribute("product", product);

			return "forward:/product/getProduct.jsp";
		}
	}
