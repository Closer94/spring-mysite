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
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp" />		

		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="${pageContext.request.contextPath }/board">
					<input type="hidden" name="a" value="deleteReply">
					<input type="hidden" name="regdate" value="${regDate }">
					<label>정말 삭제하시겠습니까?</label>
					<input type="submit" value="확인">
				</form>
				<a href="${pageContext.request.contextPath }/board">방명록 리스트</a>
			</div>
		</div>
		
		<!-- navigation 공통임으로 별도의 파일로 만들고 include -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<!-- footer 공통임으로 별도의 파일로 만들고 include -->
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
		
	</div>
</body>
<script>
	let today = new Date();   
	var keyVale = today.toLocaleString();
	var history = document.getElementById("history");

	localStorage.setItem(keyVale, window.location.href.toString());
</script>
</html>