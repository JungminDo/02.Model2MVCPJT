package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int prodNo = Integer.parseInt(request.getParameter("prodNo"));

		Product product = new Product();
		product.setProdNo(prodNo);
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setFileName(request.getParameter("fileName"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		System.out.println(request.getParameter("price") + "updateProductAction ����");

		ProductService service = new ProductServiceImpl();
		service.updateProduct(product);
		System.out.println(product + " UpdateProductAction22");

		return "forward:/getProduct.do?prodNo=" + prodNo + "&menu=manage";
		// return "forward:/product/updateProductView.jsp";
	}
}
