<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/cate">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/write">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
				<form action="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic/modify" method="post" enctype="multipart/form-data">
	 		      	<table id="admin-basic">
	 		      		<colgroup>
							<col style="width: 100px;">
							<col style="">
						</colgroup>
			      		<tr>
			      			<td><label for="textTitle">블로그 제목</label></td>
			      			<td><input id="textTitle" type="text" name="blogTitle" value="${blogVo.blogTitle }"></td>
			      		</tr>
			      		<tr>
			      			<td><label>로고이미지</label></td>
			      			<td class="text-left">
			      				<c:choose>
			      					<c:when test="${empty blogVo.logoFile }">
			      						<img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
			      					</c:when>
			      					<c:otherwise>
			      						<img src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}">
			      					</c:otherwise>
			      				</c:choose>
			      				
			      			</td>   
			      		</tr>      		
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td><input id="textLogo" type="file" name="file"></td>      			
			      		</tr>           		
			      	</table>
			      	<div id="btnArea">
			      		<button class="btn_l" type="submit" >기본설정변경</button>
			      	</div>
				</form>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
</body>


</html>
