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
<style>
	#paging-ul {
		display: flex;
		justify-content: center;
		margin-top: 20px;
	}
	#paging-ul li {
		margin: 0 10px 0 10px;
	}
</style>
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
								<td><input id="commentInput" type="text" name="cmtContent" value=""></td>
								<td><button>저장</button></td>
							</tr>
						</table>
					</form>
				</c:if>
				
				<!-- 코멘트 리스트 -->
				<table id="commentTable"  style="width: 100%; margin-top: 20px;">
					<colgroup>
						<col style="width: 10%;">
						<col style="width: 65%;">
						<col style="">
						<col style="">
					</colgroup>
					<tbody id="tbody-comment">
						
					</tbody>
				</table>
				
				<!-- 포스트 목록 -->
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
				
				<!-- 페이징 기능 -->
				<div id="paging">		
					<ul id="paging-ul">
						<c:if test="${map.prev eq true}">
							<li><a href="${pageContext.request.contextPath }/${authUser.id}?crtPage=${map.startBtnNo - 1}">◀</a></li>
						</c:if>
	
						<c:forEach begin="${map.startBtnNo }" end="${map.endBtnNo }" step="1" var="page">
							<li><a href="${pageContext.request.contextPath }/${map.blogVo.id}?crtPage=${page}">${page }</a></li>
						</c:forEach>
						 	
						<c:if test="${map.next eq true }">
							<li><a href="${pageContext.request.contextPath }/${authUser.id}?crtPage=${map.endBtnNo + 1}">▶</a></li>
						</c:if>					 
					</ul>
				</div>
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
		<!-- delete 할 때 필요한 authUser 정보 -->
		<input id="authUserName" type="hidden" name="authUserName" value="${authUser.userName }">
		
	</div>
	<!-- //wrap -->
</body>
<script>
	/* 코멘트 리스트 */
	$("document").ready(function(){
		let postNo = $("#postNo").val();
		let authUserName = $("#authUserName").val();
		
		printComments(postNo, authUserName)
		
	})
	
	function printComments(postNo, authUserName){
		/* db에서 코멘트 리스트 가져와서 화면에 출력하기 */
		$.ajax({
			url: "${pageContext.request.contextPath}/comment/getList",
			type: "post",
			data: {postNo: postNo},
			
			dateType: "json",
			success: function(commentList){

				for(let commentVo of commentList) {
					renderComment(commentVo, authUserName)
				}
				
			},error: function(XHR, status, error) {
				console.log(status + " : " + error);
			}
		})
	}

	/* 코멘트 쓰기 */
	$("#commentForm").on("submit", function(e){
		e.preventDefault();
		
		let arr = $("#commentForm").serializeArray();
		let postNo = $("#postNo").val();
		let authUserName = $("#authUserName").val();
		
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
				
				renderComment(result, authUserName)
				
				$("#commentInput").val("")
			},
			error: function(XHR, status, error) {
				console.log(status + " : " + error);
				
			}
		})
	})
	
	function renderComment(vo, authUserName) {

		let str = ""
		str += '<tr id="tr-' + vo.cmtNo + '">';
		str += '	<td>' + vo.userName + '</td>';
		str += '	<td style="text-align: left">' + vo.cmtContent + '</td>';
		str += '	<td>' + vo.regDate + '</td>';
		
		if(authUserName == vo.userName) { // 삭제버튼이 보여야 할 상황에서 안보임 
			str += '	<td><button data-cmtNo="' + vo.cmtNo + '">❌</button></td>';
		} 
		
		str += '</tr>';
		
		$("#commentTable > tbody").prepend(str);
	}
	
	/* 코멘트 삭제버튼 클릭 */
	$("#tbody-comment").on("click", "button", function(){
		let $this = $(this)
		let cmtNo  = $this.attr("data-cmtNo");

		/* db 에서 삭제 */
		$.ajax({
			url: "${pageContext.request.contextPath}/comment/delete",
			type: "post", 
			data: {cmtNo: cmtNo},
			
			dataType: "json",
			success: function(result){
				/* 화면에서 삭제 */
				
				if(result == 1) {
					$("#tr-" + cmtNo).remove()
				}
				
			},
			error: function(XHR, status, error) {
				console.log(status + " : " + error);
				
			}
		})
	})

	/* 포스트 목록 클릭했을 때 */
	$("#tbody").on("click", "tr", function(e){
		e.preventDefault();
		
		let $this = $(this);
		let postNo = $this.attr("data-postNo");
		let authUserName = $("#authUserName").val();
		
		$.ajax({
			url: "${pageContext.request.contextPath}/post/getVo",
			type: "post",
			data: {postNo: postNo},
			dataType: "json",
			success: function(postVo){
				
				$("#postTitle").html("<strong>" + postVo.postTitle + "</strong>")
				$("#postDate").html("<string>" + postVo.regDate + "</strong>")
				$("#post").text(postVo.postContent)

				$("#postNo").val(postNo) /* 코멘트 기능에서 이용 */
				
				/* 코멘트 리스트 새로 불러오기 */
				$("#tbody-comment").empty();
				printComments(postNo)
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