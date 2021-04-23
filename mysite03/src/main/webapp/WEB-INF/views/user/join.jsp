<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js" type="text/javascript"> </script>
<script>

$(function(){ // DOM이 모두 로딩 되고 이 함수가 실행된다. 따라서 btnCheck.length 가 1이 나온다.($function() 밖에서 length값을 구해보면 0이 나온다.)

	
	$("#btn-check").click(function(){
		const email = $("#input-email").val();
	
		$.ajax({
			url: "http://localhost:8080/mysite03/api/user/existemail?email="+email,
			async: true, //동기, 비동기
			data: '' , //보낼 데이터가 있을때
			dataType: 'json', //데이터 타입
			success: function(response){ //xmlRequest 로 요청했을때 response로 응답받음
				console.log(response);
			}
		})
				
	});

})

</script>
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
					<!-- <input type="hidden" name="a" value="join"> --> 
					<label class="block-label" for="name">이름</label> 
					<input id="name" name="name" type="text" value=""> 
					<label class="block-label" for="email">이메일</label> 
					<input id="input-email" name="email" type="text" value=""> 
					<input id="btn-check" type="button" value="중복체크"> 
					<label class="block-label">패스워드</label> 
					<input name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> 
						<input type="radio" name="gender" value="female" checked="checked"> 
						<label>남</label> 
						<input type="radio" name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form>
			</div>
		</div>

		<!-- navigation 공통임으로 별도의 파일로 만들고 include -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<!-- footer 공통임으로 별도의 파일로 만들고 include -->
		<c:import url="/WEB-INF/views/includes/footer.jsp" />

	</div>
</body>
</html>