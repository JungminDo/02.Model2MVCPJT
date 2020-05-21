package com.model2.mvc.service.purchase.dao;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class PurchaseDAO {

	public Purchase findPurchase(int tranNo) throws Exception {

		Connection con = DBUtil.getConnection();

		System.out.println("여기는 findPurchaseDAO");
		String sql = "SELECT * FROM TRANSACTION WHERE TRAN_NO = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
		System.out.println("파인드DAO1 : " +tranNo);
		ResultSet rs = stmt.executeQuery();
		
		Purchase purchase = new Purchase();

		UserService userService = new UserServiceImpl();
		ProductService proService = new ProductServiceImpl();

		while (rs.next()) {
			purchase.setTranNo(rs.getInt(1));
			purchase.setPurchaseProd(proService.getProduct(rs.getInt(2)));
			purchase.setBuyer(userService.getUser(rs.getString(3)));
			purchase.setPaymentOption(rs.getString(4));
			purchase.setReceiverName(rs.getString(5));
			purchase.setReceiverPhone(rs.getString(6));
			purchase.setDivyAddr(rs.getString(7));
			purchase.setDivyRequest(rs.getString(8));
			purchase.setTranCode(rs.getString(9));
			purchase.setOrderDate(rs.getDate(10));
			purchase.setDivyDate(rs.getString(11));
			System.out.println("파인드DAO1 : " +purchase);
		}
		rs.close();
		stmt.close();
		con.close();

		return purchase;
	}


	public Purchase findPurchase2(int prodNo) throws Exception {

		Connection con = DBUtil.getConnection();

		System.out.println("여기는 findPurchaseDAO");
		String sql = "SELECT * FROM TRANSACTION WHERE PROD_NO = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		System.out.println("파인드DAO1 : " +prodNo);
		ResultSet rs = stmt.executeQuery();
		
		Purchase purchase = new Purchase();

		UserService userService = new UserServiceImpl();
		ProductService proService = new ProductServiceImpl();

		while (rs.next()) {
			purchase.setTranNo(rs.getInt(1));
			purchase.setPurchaseProd(proService.getProduct(rs.getInt(2)));
			purchase.setBuyer(userService.getUser(rs.getString(3)));
			purchase.setPaymentOption(rs.getString(4));
			purchase.setReceiverName(rs.getString(5));
			purchase.setReceiverPhone(rs.getString(6));
			purchase.setDivyAddr(rs.getString(7));
			purchase.setDivyRequest(rs.getString(8));
			purchase.setTranCode(rs.getString(9));
			purchase.setOrderDate(rs.getDate(10));
			purchase.setDivyDate(rs.getString(11));
			System.out.println("파인드DAO1 : " +purchase);
		}
		rs.close();
		stmt.close();
		con.close();

		return purchase;
	}
	
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Connection con = DBUtil.getConnection();
		Product product = new Product();
		ProductService productService = new ProductServiceImpl();
		
		User user= new User();
		UserService userSerivce = new UserServiceImpl();
		
		 
		String sql = "SELECT * FROM TRANSACTION, USERS WHERE TRANSACTION.buyer_id=Users.user_id AND Users.user_id='"+buyerId+"' ";

		if (search.getSearchCondition() != null) {
			if (search.getSearchCondition().equals("0") && !search.getSearchKeyword().equals("")) {
				sql += " WHERE user_id LIKE '%" + search.getSearchKeyword() + "%'";
			} else if (search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " WHERE user_name LIKE '%" + search.getSearchKeyword() + "%'";
			} else if (search.getSearchCondition().equals("2") && !search.getSearchKeyword().equals("")) {
				sql += " WHERE tran_no LIKE '%" + search.getSearchKeyword() + "%'";
			}

		}
		sql += " ORDER BY tran_no ";
		System.out.println("ProductDAO::Original SQL :: " + sql);

		int totalCount = this.getTotalCount(sql);

		System.out.println("ProductDAO :: totalCount  :: " + totalCount);

		// ==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		System.out.println(search);
		
		List<Purchase> list = new ArrayList<Purchase>();
		
		while (rs.next()) {
			Purchase purchase = new Purchase();
			purchase.setTranNo(rs.getInt(1));
			purchase.setPurchaseProd(productService.getProduct(rs.getInt(2)));
			purchase.setBuyer(userSerivce.getUser(rs.getString(3)));
			purchase.setPaymentOption(rs.getString(4));
			purchase.setReceiverName(rs.getString(5));
			purchase.setReceiverPhone(rs.getString(6));
			purchase.setDivyAddr(rs.getString(7));
			purchase.setDivyRequest(rs.getString(8));
			purchase.setTranCode(rs.getString(9));
			purchase.setDivyDate(rs.getString(10));
			purchase.setOrderDate(rs.getDate(11));
			
			list.add(purchase);
			System.out.println("구매다오"+purchase);
		}
		// ==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		// ==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		return map;
	}

	public void insertPurchase(Purchase purchase) throws Exception {

		Connection con = DBUtil.getConnection();
		System.out.println("Insert 상품 DAO " + purchase);
		String sql = "insert into TRANSACTION values (SEQ_TRANSACTION_TRAN_NO.NEXTVAL,?,?,?,?,?,?,?,?,SYSDATE,?)";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		stmt.setString(2, purchase.getBuyer().getUserId());
		stmt.setString(3, purchase.getPaymentOption());
		stmt.setString(4, purchase.getReceiverName());
		stmt.setString(5, purchase.getReceiverPhone());
		stmt.setString(6, purchase.getDivyAddr());
		stmt.setString(7, purchase.getDivyRequest());
		stmt.setString(8, purchase.getTranCode());
		stmt.setString(9, purchase.getDivyDate());
		System.out.println("Insert 상품 DAO확인 " + purchase);
		stmt.executeUpdate();

		con.close();
	}

	public void updatePurchase(Purchase purchase) throws Exception {

		Connection con = DBUtil.getConnection();

		System.out.println(purchase + "PurchaseDAO");
		String sql = "update TRANSACTION set buyer_id=?,PAYMENT_OPTION=?,RECEIVER_NAME=?,RECEIVER_PHONE=?,"
				+ "DIVY_REQUEST=?,DIVY_DATE=? where tran_no=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchase.getBuyer().getUserId());
		stmt.setString(2, purchase.getPaymentOption());
		stmt.setString(3, purchase.getReceiverName());
		stmt.setString(4, purchase.getReceiverPhone());
		stmt.setString(5, purchase.getDivyAddr());
		stmt.setString(6, purchase.getDivyRequest());
		stmt.setString(7, purchase.getDivyDate());
		stmt.setInt(8, purchase.getTranNo());
		System.out.println(purchase + " DAO SQL");

		System.out.println("상품 업데이트다오");

		System.out.println(stmt + " DAO SQL");

		stmt.executeUpdate();

		con.close();
	}

	// 게시판 Page 처리를 위한 전체 Row(totalCount) return
	private int getTotalCount(String sql) throws Exception {

		sql = "SELECT COUNT(*) " + "FROM ( " + sql + ") countTable";

		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();

		int totalCount = 0;
		if (rs.next()) {
			totalCount = rs.getInt(1);
		}

		pStmt.close();
		con.close();
		rs.close();

		return totalCount;
	}

	// 게시판 currentPage Row 만 return
	private String makeCurrentPageSql(String sql, Search search) {
		sql = "SELECT * " + "FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " + " 	FROM (	" + sql
				+ " ) inner_table " + "	WHERE ROWNUM <=" + search.getCurrentPage() * search.getPageSize() + " ) "
				+ "WHERE row_seq BETWEEN " + ((search.getCurrentPage() - 1) * search.getPageSize() + 1) + " AND "
				+ search.getCurrentPage() * search.getPageSize();

		System.out.println("ProductDAO :: make SQL :: " + sql);

		return sql;
	}

}
