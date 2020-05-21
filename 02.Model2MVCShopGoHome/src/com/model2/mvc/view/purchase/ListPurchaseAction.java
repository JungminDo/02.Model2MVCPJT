package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class ListPurchaseAction extends Action{
	
	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Search search=new Search();
		//세션에서 유저정보 가지고오기
		HttpSession session=request.getSession(true);	
		User user = (User)session.getAttribute("user");
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null)
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		
		System.out.println("리스트프로덕트액션 "+currentPage+"현재페이즈는");
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		
		System.out.println(request.getParameter("searchCondition"+"서치 컨디션"));
		
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		System.out.println(request.getParameter("searchKeyword"+"서치 키워드"));
		
		// web.xml  meta-data 로 부터 상수 추출 
				int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
				int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
				search.setPageSize(pageSize);
				
				// Business logic 수행
				PurchaseService purchaseService=new PurchaseServiceImpl();
				// 유저 아이디 가지고 오기
				Map<String , Object> map=purchaseService.getPurchaseList(search, user.getUserId());
				System.out.println("리스트프로덕트 액션 1번");
				Page resultPage	= 
							new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
				System.out.println("ListPurchaseAction ::"+resultPage);
				System.out.println(user);
				// Model 과 View 연결
				request.setAttribute("list", map.get("list"));
				request.setAttribute("resultPage", resultPage);
				request.setAttribute("search", search);
				
				return "forward:/purchase/listPurchase.jsp";
			}
		}