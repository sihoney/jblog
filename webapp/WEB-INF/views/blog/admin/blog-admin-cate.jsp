<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn  selected"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/cate">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/write">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
						<!-- 리스트 영역 -->
						<%-- 
						<c:forEach items="${cateList }" var="cateVo">
			      			<tr>
								<td>${cateVo.cateNo }</td>
								<td>${cateVo.cateName }</td>
								<td>${cateVo.postCnt }</td>
								<td>${cateVo.description }</td>
							    <td class='text-center'>
							    	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
							    </td>
							</tr>						
						</c:forEach> --%>
						
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="newCateName" type="text" name="name" value="" required></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="newCateDesc" type="text" name="desc" required></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
</body>
<script>
	/* 리스트 불러오기 */
	$("document").ready(function(){
		fetchList();
	})

	/* 카테고리 추가 */
	$("#btnAddCate").on("click", function(){

		let newName = $("#newCateName");
		let newDesc = $("#newCateDesc");
		
		let obj = {
				cateName : newName.val(),
				description: newDesc.val()
		}
		/* db에 추가 */
		$.ajax({
			url: "${pageContext.request.contextPath}/cate/add",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(obj),
			
			dataType: "json",
			success: function(result) {
				
				render(result)
				
				newName.val("");
				newDesc.val("");
				
			},
			error:function(request,status,error){
			    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		})
	})
	
	/* 삭제 버튼 클릭 */
	$("#cateList").on("click", ".btnCateDel", function(){
		let $this = $(this);
		let cateNo = $this.attr("data-cateNo");
		
		if(confirm("카테고리를 삭제하면 관련 포스트도 삭제됩니다. 삭제하시겠습니까?") == true) {
			console.log("삭제")
			
			$.ajax({
				url: "${pageContext.request.contextPath}/cate/deleteCate",
				type: "post",
				data: {cateNo: cateNo},
				
				dataType: "json",
				success: function(result) {
					
					console.log(result)
					
					if(result == "success") {
						$("#tr-" + cateNo).remove();	
					}
				
				},
				error:function(request,status,error){
				    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			})
		} 
	})
	
	function fetchList(){
		$.ajax({
			url: "${pageContext.request.contextPath}/cate/getCateList",
			type: "post",
			
			dateType: "json",
			success: function(cateList) { // json -> js 변환
				/*성공시 처리해야될 코드 작성*/
				
				for(let cvo of cateList) {
					render(cvo)
				}
									
			}, 
			error: function(XHR, status, error) {
				console.log(status + " : " + error);
			}
		})
	}
	
	function render(cvo){
		
		let str = "";
		str += "<tr id=tr-" + cvo.cateNo + ">";
		str += "	<td>" + cvo.cateNo + "</td>";
		str += "	<td>" + cvo.cateName + "</td>";
		str += "	<td>" + cvo.postCnt + "</td>";
		str += "	<td>" + cvo.description + "</td>";
		str += "    <td class='text-center'>";
		str += '	   	<img class="btnCateDel" data-cateNo="' + cvo.cateNo + '"src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += "    </td>";
		str += "</tr>";
		
		$("#cateList").append(str);
		
	}
	
</script>



</html>