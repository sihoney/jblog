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
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<c:choose>
						<c:when test="${empty map.blogVo.logoFile}">
							<!-- 기본이미지 -->
							<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
						</c:when>
						<c:otherwise>
							<!-- 사용자업로드 이미지 -->
							<img id="proImg" src="${pageContext.request.contextPath}/upload/${map.blogVo.logoFile}">
						</c:otherwise>
					</c:choose>
					
					<!-- 사용자업로드 이미지 -->
					<%-- <img id="proImg" src=""> --%>
					
					<div id="nick">${map.blogVo.userName}(${map.blogVo.id })님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${map.cateList }" var="cateVo">
							<li data-cateNo="${cateVo.cateNo }"><a href="">${cateVo.cateName }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
				<c:choose>
					<c:when test="${empty map.postVo }">
						<div id="postBox" class="clearfix">
								<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
								<div id="postDate" class="text-left"><strong></strong></div>
								<div id="postNick"></div>
						</div>
					    
						<div id="post" >
						</div>
						<!-- 등록된 글이 없을 때 -->
					</c:when>
					<c:otherwise>
						<div id="postBox" class="clearfix">
								<div id="postTitle" class="text-left"><strong>${map.postVo.postNo }.${map.postVo.postTitle }</strong></div>
								<div id="postDate" class="text-left"><strong>${map.postVo.regDate }</strong></div>
								<div id="postNick">${map.blogVo.userName}(${map.blogVo.id })님</div>
								
								<!-- 코멘트 기능에서 참고 -->
								<input id="postNo" type="hidden" name="postNo" value="${map.postVo.postNo }">
						</div>
						<!-- //postBox -->
		
						<div id="post" >${map.postVo.postContent }</div>
						<!-- //post -->					
					</c:otherwise>
				</c:choose>
				
				<!-- 코멘트 기능 -->
				<c:if test="${not empty authUser}">
					<form id="commentForm">
						<table border="1" style="width: 100%; margin-top: 20px;">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 80%;">
								<col style="width: 10%;">
							</colgroup>
							<tr>
								<td>${authUser.userName }</td>
								<td><input type="text" name="cmtContent" value=""></td>
								<td><button>저장</button></td>
							</tr>
						</table>
					</form>
				</c:if>
				
				<table id="commentTable"  style="width: 100%; margin-top: 20px;">
					<colgroup>
						<col style="width: 10%;">
						<col style="width: 80%;">
						<col style="">
					</colgroup>
					<tbody id="tbody-comment">
						
					</tbody>
				</table>
				
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table id="cate-table">
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						<tbody id="tbody">
							<c:forEach items="${map.postList }" var="postVo">
								<tr data-postNo="${postVo.postNo }">
									<td class="text-left"><a href="">${postVo.postNo }. ${postVo.postTitle }</a></td>
									<td class="text-right">${postVo.regDate }</td>
								</tr>
							</c:forEach>
						</tbody>	
						
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
		
	
	</div>
	<!-- //wrap -->
</body>
<script>
	/* 코멘트 리스트 */
	$("document").ready(function(){
		let postNo = $("#postNo").val();
		
		/* db에서 코멘트 리스트 가져오기 */
		$.ajax({
			url: "${pageContext.request.contextPath}/comment/getList",
			type: "post",
			data: {postNo: postNo},
			
			dateType: "json",
			success: function(commentList){

				for(let commentVo of commentList) {
					renderComment(commentVo)
				}
				
			},error: function(XHR, status, error) {
				console.log(status + " : " + error);
				
			}
			
		})
		
	})

	/* 코멘트 쓰기 */
	$("#commentForm").on("submit", function(e){
		e.preventDefault();
		
		let arr = $("#commentForm").serializeArray();
		let postNo = $("#postNo").val();
		
		let obj = {
				postNo: postNo,
				cmtContent: arr[0].value
		}
		
		console.log(obj)
		
		$.ajax({
			url: "${pageContext.request.contextPath}/comment/add",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(obj),
			
			dataType: "json",
			success: function(result){
				
				renderComment(result)
			},
			error: function(XHR, status, error) {
				console.log(status + " : " + error);
				
			}
		})
	})
	
	function renderComment(vo) {
		let str = ""
		str += '<tr>';
		str += '	<td>' + vo.userName + '</td>';
		str += '	<td style="text-align: left">' + vo.cmtContent + '</td>';
		str += '	<td>' + vo.regDate + '</td>';
		str += '	<td><button data-cmtNo+"' + vo.cmtNo + '">❌</button></td>';
		str += '</tr>';
		
		$("#commentTable > tbody").prepend(str);
	}
	
	/* 코멘트 삭제버튼 클릭 */
	$("#tbody-comment").on("click", "button", function(){
		let $this = $(this)
		let cmtNo  = $this.data("cmtNo")
		
		console.log(cmtNo)
	})

	/* 포스트 목록 클릭했을 때 */
	$("#tbody").on("click", "tr", function(e){
		e.preventDefault();
		
		let $this = $(this);
		let postNo = $this.attr("data-postNo");
		
		$.ajax({
			url: "${pageContext.request.contextPath}/post/getVo",
			type: "post",
			data: {postNo: postNo},
			dataType: "json",
			success: function(postVo){
				
				$("#postTitle").html("<strong>" + postVo.postTitle + "</strong>")
				$("#postDate").html("<string>" + postVo.regDate + "</strong>")
				$("#post").text(postVo.postContent)

				$("#postNo").val(postNo)
			},
			error: function(XHR, status, error) {
				console.log(status + " : " + error);
				
			}
			
		})
		
	})
	
	/* 카테고리 항목 클릭했을 때 */
	$("#cateList").on("click", "li", function(e){
		e.preventDefault();
		
		let $this = $(this);
		let cateNo = $this.attr("data-cateNo");

		$.ajax({
			url:"${pageContext.request.contextPath}/post/getList",
			type: "post",
			data: {cateNo: cateNo},
			
			dataType: "json",
			success: function(postList){
				
				$("#tbody > tr").remove();
				
				for(let postVo of postList) {
					render(postVo)
				}
				
			}, 
			error: function(){
				
			}
		})
	})
	
	function render(vo) {

		let str = "";
		str += '<tr>';
		str += '	<td class="text-left"><a href="">' + vo.postNo + '.' + vo.postTitle + '</a></td>';
		str += '	<td class="text-right">' + vo.regDate + '</td>';
		str += '</tr>';
		
		$("#tbody").append(str);
		
	}
</script>
</html>