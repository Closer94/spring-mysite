<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>

.boardTable tr:nth-child(even) {
	background-color: #ffd4e9;
}

.boardTable tr:nth-child(odd) {
	background-color: #d4e9ff;
}


</style>

<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body id="main">
	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody class="boardTable">
					<c:set var="count" value="${fn:length(list)}" />
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${count-status.index }</td>
							<td><a
								href="${pageContext.request.contextPath }/board?a=view&title=${vo.title }&regdate=${vo.regDate }"
								style="text-align: left; padding-left: 40px;">${vo.title }</a></td>
							<td>${vo.writerId }</td>
							<td>${vo.viewCnt }</td>
							<td>${vo.regDate }</td>
							<c:if test="${vo.writerId == authUser.name }">
								<td>
									<a href="${pageContext.request.contextPath }/board?a=deleteform&regDate=${vo.regDate }" class="del">삭제</a>
								</td>
							</c:if>
						</tr>
						
						<c:forEach items="${replyList }" var="replyVo" varStatus="status">
							<tr>
								<c:if test="${vo.no == replyVo.group_no}">
									<td></td>
									<td>
										<a href="${pageContext.request.contextPath }/board?a=viewReply&title=${replyVo.title }&regdate=${replyVo.regDate }" style="text-align: left; padding-left: 40px;">
											<img src="${pageContext.request.contextPath }/assets/images/reply.png" />${replyVo.title }
										</a>
									</td>
									<td>${replyVo.writerId }</td>
									<td>${replyVo.viewCnt }</td>
									<td>${replyVo.regDate }</td>
									<c:if test="${replyVo.writerId == authUser.name }">
									<td><a href="${pageContext.request.contextPath }/board?a=deleteReplyform&regDate=${replyVo.regDate}" class="del">삭제</a></td>
									</c:if>
								</c:if>
							</tr>
						</c:forEach>
						
					</c:forEach>
					</tbody>
				</table>

				<!-- pager 추가 -->

				<!-- 현재 page와 시작 page, 마지막 page 값을 구해서 각각의 변수에 담았다. -->
				<!-- ****************************************************** -->
				<c:set var="page" value="${(empty param.p)?1:param.p}" />
				<c:set var="startNum" value="${page-(page-1)%5}" />
				<c:set var="lastNum"
					value="${fn:substringBefore(Math.ceil(count/10), '.') }" />
				<!-- ****************************************************** -->
				<div class="pager">
					<ul>
						<li>
						<c:choose>
							<c:when test="${startNum > 1}">
								<a href="${pageContext.request.contextPath }/board?p=${startNum-1}" >◀</a>
							</c:when>
							<c:otherwise>
								<span onclick="alert('이전 페이지가 없습니다.');">◀</span>
							</c:otherwise>
						</c:choose>
						</li>
						<c:forEach var="i" begin="0" end="4">
							<li>
								<a href="${pageContext.request.contextPath }/board?p=${startNum+i}">${startNum+i}</a>
							</li>
						</c:forEach>
						<li>
							<c:choose>
								<c:when test="${startNum+4<lastNum}">
									<a href="${pageContext.request.contextPath }/board?p=${startNum+i}">▶</a>
								</c:when>
								<c:otherwise>
									<span onclick="alert('다음 페이지가 없습니다.');">▶</span>
								</c:otherwise>
							</c:choose>
						</li>
						
					</ul>
				</div>


				<!-- pager 추가 -->

				<c:if test="${not empty authUser }">
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board?a=writeform"
							id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>

		<!-- navigation 공통임으로 별도의 파일로 만들고 include -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<!-- footer 공통임으로 별도의 파일로 만들고 include -->
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
	
	<h1>클릭한 게시판</h1>
	
</body>

<!-- 방문 URL 기록
<script>
	let today = new Date();   
	var keyVale = today.toLocaleString();
	localStorage.setItem(keyVale, window.location.href.toString());
	for(let i = 0; i < localStorage.length; i++){
		var key = localStorage.key(i);
		document.write(key + "<br/>");
		var value = localStorage.getItem(key);
		document.write(value + "<br/>");
		
	}
</script>
 -->
<script>
	 
	window.onload = function(){
	var x = document.getElementById("main");
	var aTags = x.getElementsByTagName("a");
	for(let i = 0; i < aTags.length; i++){
		aTags[i].addEventListener('click', function(event){
	       
	        var today = new Date();  
	        var keyValue = event.target.innerHTML;
			localStorage.setItem(today, keyValue);

	    });
	}
	
	}
	
	for(let i = 0; i < localStorage.length; i++){
		var key = localStorage.key(i);
		var value = localStorage.getItem(key);
		document.write(value + "<br/>");
	}
	
</script>
</html>