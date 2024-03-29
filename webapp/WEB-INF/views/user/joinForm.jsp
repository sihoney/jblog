<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JBlog</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id" required></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      			<td><input id="check" type="hidden" name="check" value=""></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value="" required></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value="" required></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y" required>
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
	</div>

</body>
<script>
	let checkResult = $("#check");
	
	$("#btnIdCheck").on("click", function(){
		let id = $("#txtId").val();
		
		$.ajax({
			url: "${pageContext.request.contextPath}/user/idCheck",
			type: "post",
			data: {id: id},
			
			dataType: "json",
			success: function(result){
				if(result == 1) {
					$("#tdMsg").text("다른 아이디로 가입해 주세요.");
					checkResult.val(false);
				} else {
					$("#tdMsg").text("사용할 수 있는 아이디 입니다.");
					checkResult.val(true);
				}
				
			},
			error: function(XHR, status, error){
				console.log(status + " : " + error);
			}
		})
	})
	
	$("#joinForm").on("submit", function(e){
		e.preventDefault();
		let arr = $("#joinForm").serializeArray();
		
		let obj = {
				id: arr[0].value,
				password: arr[2].value,
				userName: arr[3].value
		}

		if(checkResult.val() == "true") {
			
			$.ajax({
				url: "${pageContext.request.contextPath}/user/join",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(obj),
				
				dataType: "json",
				success: function(result){
					console.log(result)
					
					if(result == "success") {
						location.href = "${pageContext.request.contextPath}/user/joinSuccess";
					}
					
				},
				error: function(XHR, status, error){
					console.log(status + " : " + error);
				}
			})	
					
		} else {
			$("#tdMsg").text("아이디 중복 체크 해주세요.");
			checkResult.val(false);
		}
		
	})
	
</script>

</html>