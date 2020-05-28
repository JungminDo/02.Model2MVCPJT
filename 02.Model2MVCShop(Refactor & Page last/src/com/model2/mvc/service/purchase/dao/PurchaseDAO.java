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

		System.out.println("����� findPurchaseDAO");
		String sql = "SELECT * FROM TRANSACTION WHERE TRAN_NO = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
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
			System.out.println("���ε�DAO1 : " +purchase);
		}
		rs.close();
		stmt.close();
		con.close();

		return purchase;
	}


//	public Purchase findPurchase2(int prodNo) throws Exception {
//
//		Connection con = DBUtil.getConnection();
//
//		System.out.println("����� findPurchaseDAO");
//		String sql = "SELECT * FROM TRANSACTION WHERE PROD_NO = ?";
//
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setInt(1, prodNo);
//		System.out.println("���ε�DAO1 : " +prodNo);
//		ResultSet rs = stmt.executeQuery();
//		
//		Purchase purchase = new Purchase();
//
//		UserService userService = new UserServiceImpl();
//		ProductService proService = new ProductServiceImpl();
//
//		while (rs.next()) {
//			purchase.setTranNo(rs.getInt(1));
//			purchase.setPurchaseProd(proService.getProduct(rs.getInt(2)));
//			purchase.setBuyer(userService.getUser(rs.getString(3)));
//			purchase.setPaymentOption(rs.getString(4));
//			purchase.setReceiverName(rs.getString(5));
//			purchase.setReceiverPhone(rs.getString(6));
//			purchase.setDivyAddr(rs.getString(7));
//			purchase.setDivyRequest(rs.getString(8));
//			purchase.setTranCode(rs.getString(9));
//			purchase.setOrderDate(rs.getDate(10));
//			purchase.setDivyDate(rs.getString(11));
//			System.out.println("���ε�DAO1 : " +purchase);
//		}
//		rs.close();
//		stmt.close();
//		con.close();
//
//		return purchase;
//	}
	
//	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		Connection con = DBUtil.getConnection();
//		Product product = new Product();
//		ProductService productService = new ProductServiceImpl();
//		
//		User user= new User();
//		UserService userSerivce = new UserServiceImpl();
//		
//		 
//		String sql = "SELECT * FROM TRANSACTION, USERS WHERE TRANSACTION.buyer_id=Users.user_id AND Users.user_id='"+buyerId+"' ";
//
//		if (search.getSearchCondition() != null) {
//			if (search.getSearchCondition().equals("0") && !search.getSearchKeyword().equals("")) {
//				sql += " WHERE user_id LIKE '%" + search.getSearchKeyword() + "%'";
//			} else if (search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
//				sql += " WHERE user_name LIKE '%" + search.getSearchKeyword() + "%'";
//			} else if (search.getSearchCondition().equals("2") && !search.getSearchKeyword().equals("")) {
//				sql += " WHERE tran_no LIKE '%" + search.getSearchKeyword() + "%'";
//			}
//
//		}
//		sql += " ORDER BY tran_no ";
//		System.out.println("ProductDAO::Original SQL :: " + sql);
//
//		int totalCount = this.getTotalCount(sql);
//
//		System.out.println("ProductDAO :: totalCount  :: " + totalCount);
//
//		// ==> CurrentPage �Խù��� �޵��� Query �ٽñ���
//		sql = makeCurrentPageSql(sql, search);
//		
//		PreparedStatement pStmt = con.prepareStatement(sql);
//		ResultSet rs = pStmt.executeQuery();
//		
//		System.out.println(search);
//		
//		List<Purchase> list = new ArrayList<Purchase>();
//		
//		while (rs.next()) {
//			Purchase purchase = new Purchase();
//			purchase.setTranNo(rs.getInt(1));
//			purchase.setPurchaseProd(productService.getProduct(rs.getInt(2)));
//			purchase.setBuyer(userSerivce.getUser(rs.getString(3)));
//			purchase.setPaymentOption(rs.getString(4));
//			purchase.setReceiverName(rs.getString(5));
//			purchase.setReceiverPhone(rs.getString(6));
//			purchase.setDivyAddr(rs.getString(7));
//			purchase.setDivyRequest(rs.getString(8));
//			purchase.setTranCode(rs.getString(9));
//			purchase.setDivyDate(rs.getString(10));
//			purchase.setOrderDate(rs.getDate(11));
//			
//			list.add(purchase);
//			System.out.println("���Ŵٿ�"+purchase);
//		}
//		// ==> totalCount ���� ����
//		map.put("totalCount", new Integer(totalCount));
//		// ==> currentPage �� �Խù� ���� ���� List ����
//		map.put("list", list);
//
//		rs.close();
//		pStmt.close();
//		con.close();
//
//		return map;
//	}

	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		Connection con = DBUtil.getConnection();
		
		Product product = new Product();
		ProductService servicePro = new ProductServiceImpl();

		User userVO = new User();
		UserService serviceUser = new UserServiceImpl();

		String sql = "SELECT * FROM TRANSACTION, users where TRANSACTION.buyer_id=USERS.user_id AND USERS.user_id = '"+buyerId+"' ORDER BY tran_no";
		
		int totalCount = this.getTotalCount(sql);		
		sql = this.makeCurrentPageSql(sql, search);
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		

		HashMap<String, Object> map = new HashMap();
		List<Purchase> list = new ArrayList<Purchase>();
		
		while(rs.next()) {
				Purchase purchase = new Purchase();
				purchase.setTranNo(rs.getInt(1));
				purchase.setPurchaseProd(servicePro.getProduct(rs.getInt(2)));
				purchase.setBuyer(serviceUser.getUser(rs.getString(3)));
				purchase.setPaymentOption(rs.getString(4));
				purchase.setReceiverName(rs.getString(5));
				purchase.setReceiverPhone(rs.getString(6));
				purchase.setDivyAddr(rs.getString(7));
				purchase.setDivyRequest(rs.getString(8));
				purchase.setTranCode(rs.getString(9));
				purchase.setDivyDate(rs.getString(10));
				purchase.setOrderDate(rs.getDate(11));
				
				list.add(purchase);
			}
		map.put("totalCount", new Integer(totalCount));
		map.put("list", list);
		
		rs.close();
		pStmt.close();
		con.close();
		
		return map;
	}
	
	public void insertPurchase(Purchase purchase) throws Exception {

		Connection con = DBUtil.getConnection();
		System.out.println("Insert ��ǰ DAO " + purchase);
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
		System.out.println("Insert ��ǰ DAOȮ�� " + purchase);
		stmt.executeUpdate();

		con.close();
	}

	public void updatePurchase(Purchase purchase) throws Exception {

		Connection con = DBUtil.getConnection();

		System.out.println(purchase + "PurchaseDAO");
		String sql = "UPDATE TRANSACTION SET buyer_id=?, payment_option=?, receiver_name=?,  receiver_phone=?, demailaddr=?, dlvy_request=?, dlvy_date=? WHERE tran_no=?";

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

		System.out.println("��ǰ ������Ʈ�ٿ�");

		System.out.println(stmt + " DAO SQL");

		stmt.executeUpdate();
		con.commit();
		stmt.close();
		con.close();
	}

	public void updateTranCode(Purchase purchase) throws Exception {
		System.out.println("��ǰ �ٿ�"+purchase);
		String tranCode = (purchase.getTranCode()).trim();
		String TranCode = null;

		Connection con = DBUtil.getConnection();
		String sql = "UPDATE TRANSACTION SET ";
		if (tranCode.equals("1")) {
			sql += "  tran_status_code='2' ";
			TranCode = "2";
		} else if (tranCode.equals("2")) {
			sql += " tran_status_code='3' ";
			TranCode = "3";
		} else if (tranCode.equals("3")) {
			sql += " tran_status_code='4' ";
			TranCode = "4";;
		}

		sql += " WHERE tran_no=?";

		System.out.println(sql + " > update tran sql");

		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, purchase.getTranNo());
		pStmt.executeUpdate();
		con.commit();
		
		pStmt.close();
		con.close();	
	}
	
	
	
	private int getTotalCount(String sql) throws Exception{
		sql = "SELECT COUNT(*) FROM ("+sql+" ) countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if(rs.next()) {
			totalCount = rs.getInt(1);
		}
		pStmt.close();
		rs.close();
		con.close();
		
		return totalCount;
	}
	
	private String makeCurrentPageSql(String sql, Search search) {
		sql = "SELECT * FROM (SELECT inner_table.*, ROWNUM AS row_seq FROM ( "+sql+" ) inner_table WHERE ROWNUM <= "+search.getCurrentPage()*search.getPageSize()+" ) WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1)+" AND "+search.getCurrentPage()*search.getPageSize();
		System.out.println("PurchaseDAO :: make SQL :: "+ sql);
	return sql;
	}
}