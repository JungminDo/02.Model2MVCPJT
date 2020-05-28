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
		System.out.println("�����δ�Ʈ�׼�");
		
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		System.out.println(prodNo);
		
		ProductService service=new ProductServiceImpl();
		
		Product product = service.getProduct(prodNo);
		
		System.out.println(product+"product GetProductAction");
		
		request.setAttribute("product", product);
		
		System.out.println("���� VO �����δ�Ʈ�׼�");
		

		request.setAttribute("product", product);

			return "forward:/product/getProduct.jsp";
		}
	}
