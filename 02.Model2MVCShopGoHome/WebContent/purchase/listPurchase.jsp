<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%--
<%@ page import="java.util.*" %>
<%@ page import="com.model2.mvc.service.domain.Purchase" %>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>
<%@page import="com.model2.mvc.common.Page"%>
<%@ page import="com.model2.mvc.common.Search" %>
<%
	List<Purchase> list= (List<Purchase>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	System.out.println("리스트 프로덕트");
	Search search = (Search)request.getAttribute("search");
	//==> null 을 ""(nullString)으로 변경
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
%>
--%>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

function fncGetPurchaseList(currentPage) {
	
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">
<form name="detailForm" action="/listPurchase.do?"
								 method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
				
							상품 조회3
			
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
	<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>사용자ID</option>
	<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>사용자이름</option>
	<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>번호</option>
		
			</select>
			<input 	type="text" name="searchKeyword"  value="${! empty search.searchKeyword ? search.searchKeyword : ""}" 
							class="ct_input_g" style="width:200px; height:19px" >
		</td>
			
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
				
						<a href="javascript:fncGetPurchaseList(1);">검색</a>
	
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >
			전체  ${resultPage.totalCount} 건수,	현재 ${resultPage.currentPage}  페이지
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">사용자아이디</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">사용자이름</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송상태</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">만족도</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

			<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center"><a href="/getPurchase.do?tranNo=${purchase.tranNo}">${ i }</a></td>
			<td></td>
			<td align="left"><a href="/getUser.do?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a></td>
			<td></td>
			<td align="left">${purchase.receiverName}</td>
			<td></td>
			<td align="left">${purchase.receiverPhone}</td>
			<td></td>
			<td align="left">
			<c:if test="${fn:trim(purchase.tranCode)=='1'}"> 현재 구매완료전 입니다. </c:if>
			<c:if test="${fn:trim(purchase.tranCode)=='2'}"> 현재 구매완료 입니다. </c:if>
			<c:if test="${fn:trim(purchase.tranCode)=='3'}"> 현재 배송 중 입니다. </c:if>
			<c:if test="${fn:trim(purchase.tranCode)=='4'}"> 현재 배송완료 입니다. </c:if>
		</td>
		<td></td>
			<td align="left"> 나빠요
		</tr>
		<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>
	
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
	
		<jsp:include page="../common/pageNavigator.jsp"/>	

			</td>
			</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</body>
</html>
