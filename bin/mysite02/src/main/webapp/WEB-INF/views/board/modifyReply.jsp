<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board">
					<input type = "hidden" name = "a" value="modifyReplyConfirm">
					<input type = "hidden" name = "originTitle" value="${vo.title}">
					<input type = "hidden" name = "originContent" value="${vo.content}">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글수정</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="${vo.title}"></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content">
									${vo.content }
								</textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board">취소</a> 
						<input type="submit" value="수정">
					</div>
				</form>
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