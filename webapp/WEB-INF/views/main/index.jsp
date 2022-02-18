<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog2.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div id="center-content">
		
		<!--메인 해더 자리 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		
		<form id="search-form">
			<fieldset>
				<input type="text" name="keyword" >
				<button id="btnSearch" type="submit" >검색</button>
			</fieldset>
			
			<fieldset>
				<label for="rdo-title">블로그 제목</label> 
				<input id="rdo-title" type="radio" name="kwdOpt" value="optTitle" > 
				
				<label for="rdo-userName">블로거 이름</label> 
				<input id="rdo-userName" type="radio" name="kwdOpt" value="optName" > 
			</fieldset>
		</form>
		
		<div border="1" id="resultList"></div>
		
		<!-- 페이징 기능 -->
		<div id="paging">
			<ul style="display: flex; justify-content: center;"></ul>
		</div>
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
	
	</div>
	<!-- //center-content -->
</body>
<script>
	/* 검색 결과 리스트 */
	$("#search-form").on("submit", function(e){
		e.preventDefault();

		let arr = $("#search-form").serializeArray();
		
		let obj = {
				keyword: arr[0].value,
				kwdOpt: arr[1].value
		}

		$.ajax({
			url: "${pageContext.request.contextPath}/post/search",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(obj),
			
			dataType: "json",
			success: function(map){
				
				$("#resultList").empty()
				
				for(let vo of map.searchList) {
					renderList(vo)
				}
				
				/* 페이징 작업 */
				renderPaging(map.startBtnNo, map.endBtnNo)
			},
			error: function(XHR, status, error) {
				console.log(status + " : " + error);
			}
		})

	})
	
	function renderPaging(startBtnNo, endBtnNo) {
		let str = '';
		
		for(let i = startBtnNo; i <= endBtnNo; i++) {
			str += '<li><a href="${pageContext.request.contextPath}/post/search?crtPage=' + i + '">'+ i +'</a></li>'
		}
		
		$("#paging > ul").append(str)
	}
	
	function renderList(vo) {

		let str = '';
		str += '<table data-id="' + vo.id + '" data-postNo="' + vo.postNo + '" border="1" style="width: 100%; margin-top: 20px;">'
		str += '	<colgroup>'
		str += '		<col style="width: 10%">'
		str += '		<col style="width: 40%">'
		str += '		<col style="width: 15%">'
		str += '		<col style="">'
		str += '	</colgroup>'
		str += '	<tr>'
		str += '		<td style="text-align:center;"><img style="width: 80px;" src="${pageContext.request.contextPath}/upload/'+ vo.logoFile +'"></td>'
		str += '		<td style="text-align:center;font-weight: bold; font-size: 1.2rem;">'+ vo.postTitle +'</td>'
		str += '		<td style="text-align:center;font-weight: bold; font-size: 1.2rem;">'+ vo.userName + '(' + vo.id + ')' +'</td>'
		str += '		<td style="text-align:center;font-weight: bold; font-size: 1.2rem;">'+ vo.regDate +'</td>'
		str += '	</tr>'
		str += '</table>'
		
		$("#resultList").append(str);
	}
	
	/* 해당 글 클릭 --> 페이지로 이동 */
	$("#resultList").on("click", "table", function(){
		
		let $this = $(this);
		let id = $this.data("id")
		let postNo = $this.data("postno")

		location.href='${pageContext.request.contextPath}/' + id + '?postNo=' + postNo;

	})
	
	
</script>
</html>