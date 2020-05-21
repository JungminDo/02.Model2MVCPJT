package com.model2.mvc.view.product;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		
		Product product=new Product();
		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setFileName(request.getParameter("fileName"));
		product.setManuDate(request.getParameter("manuDate"));
		System.out.println(request.getParameter("manuDate")+"여기 제조날짜");
		System.out.println(request.getParameter("price")+"여기 프라이스");
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		System.out.println(request.getParameter("price")+"updateProductAction 가격");
		ProductService service=new ProductServiceImpl();
		System.out.println(product+" UpdateProductAction11");
		service.updateProduct(product);
		
		
		System.out.println(product+" UpdateProductAction22");
		
	
		return "redirect:/getProduct.do?prodNo="+prodNo;
	}
}
