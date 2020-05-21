package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  ProductServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdNo(Integer.parseInt("1001"));
		product.setProdName("자전거");
		product.setProdDetail("111112222222");
		product.setManuDate("20180205");
		product.setPrice(Integer.parseInt("11111"));
		product.setFileName("testProduct");
		
		productService.addProduct(product);
		
		product = productService.getProduct(1001);

		//==> console 확인
		//System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals(1001, product.getProdNo());
		Assert.assertEquals("자전거", product.getProdName());
		Assert.assertEquals("111112222222", product.getProdDetail());
		Assert.assertEquals("20180205", product.getManuDate());
		Assert.assertEquals(11111, product.getPrice());
		Assert.assertEquals("testProduct", product.getFileName());

	}
	
	@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		//==> 필요하다면...
//			product.setProductId("testProductId");
//			product.setProductName("testProductName");
//			product.setPassword("testPasswd");
//			product.setSsn("1111112222222");
//			product.setPhone("111-2222-3333");
//			product.setAddr("경기도");
//			product.setEmail("test@test.com");
		
		product = productService.getProduct(1001);

		//==> console 확인
		//System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals(1001, product.getProdNo());
		Assert.assertEquals("자전거", product.getProdName());
		Assert.assertEquals("111112222222", product.getProdDetail());
		Assert.assertEquals("20180205", product.getManuDate());
		Assert.assertEquals(11111, product.getPrice());
		Assert.assertEquals("testProduct", product.getFileName());

	}
	/*
	@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct("testProductId");
		Assert.assertNotNull(product);
		
		Assert.assertEquals("testProductName", product.getProductName());
		Assert.assertEquals("111-2222-3333", product.getPhone());
		Assert.assertEquals("경기도", product.getAddr());
		Assert.assertEquals("test@test.com", product.getEmail());

		product.setProductName("change");
		product.setPhone("777-7777-7777");
		product.setAddr("change");
		product.setEmail("change@change.com");
		
		productService.updateProduct(product);
		
		product = productService.getProduct("testProductId");
		Assert.assertNotNull(product);
		
		//==> console 확인
		//System.out.println(product);
			
		//==> API 확인
		Assert.assertEquals("change", product.getProductName());
		Assert.assertEquals("777-7777-7777", product.getPhone());
		Assert.assertEquals("change", product.getAddr());
		Assert.assertEquals("change@change.com", product.getEmail());
	 }
	 
	//@Test
	public void testCheckDuplication() throws Exception{

		//==> 필요하다면...
//			Product product = new Product();
//			product.setProductId("testProductId");
//			product.setProductName("testProductName");
//			product.setPassword("testPasswd");
//			product.setSsn("1111112222222");
//			product.setPhone("111-2222-3333");
//			product.setAddr("경기도");
//			product.setEmail("test@test.com");
//			
//			productService.addProduct(product);
		
		//==> console 확인
		//System.out.println(productService.checkDuplication("testProductId"));
		//System.out.println(productService.checkDuplication("testProductId"+System.currentTimeMillis()) );
	 	
		//==> API 확인
		Assert.assertFalse( productService.checkDuplication("testProductId") );
	 	Assert.assertTrue( productService.checkDuplication("testProductId"+System.currentTimeMillis()) );
		 	
	}
	*/
	 //==>  주석을 풀고 실행하면....
	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProductId() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("admin");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProductName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("SCOTT");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}