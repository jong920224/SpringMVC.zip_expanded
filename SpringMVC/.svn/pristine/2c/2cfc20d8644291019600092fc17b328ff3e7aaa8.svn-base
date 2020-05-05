<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

	<title>고객센터</title>
</head>
<body>
	<h1 style="text-align:center"><strong><a href="/index.jsp">SOOLOG</a></strong></h1>
	<hr>
	<div class="container">
		<h1 style="text-align:center"><strong>고객센터</strong></h1>
		<table class="table table-bordered">
			<thead>
			<tr>
				<th>번호</th>
				<th style="width:60%;">제목</th>
				<th>작성자</th> 
				<th>등록일시</th>
				<th>조회수</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="row" items="${boardList}">
				<tr style="cursor:pointer;">
					<td>${row.BOARD_NUM}</td>
					<td onclick="location.href='/boardDetail.do?boardNum=${row.BOARD_NUM}'"><strong>${row.BOARD_TITLE}</strong></td>
					<td>${row.BOARD_WRITER}</td>
					<td>${row.REG_DATE}</td>
					<td style="text-align:right;">${row.READ_COUNT}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<hr/>
	
		<c:choose>
			<c:when test ="${not empty sessionScope.USER_ID}">
				<a class="btn btn-primary" onclick="location.href='/boardWrite.do'">글쓰기</a>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		
		<div class="text-center">
			<ul class="pagination">
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
			</ul>
		</div>
		
	</div>
</body>
</html>
