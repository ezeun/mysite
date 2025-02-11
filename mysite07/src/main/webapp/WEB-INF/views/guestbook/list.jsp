<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<%
	pageContext.setAttribute("newline","\n");
%>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/guestbook/add" method="post">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE="등록"></td>
						</tr>
					</table>
				</form>
				
				<c:set var="listCount" value="${fn:length(list)}"/>
				<c:forEach var="vo" items="${list}" varStatus="index">
					<br>
					<table width=510 border=1>
						<tr>
							<td>[${listCount-index.count+1}]</td>
							<td>${vo.name}</td>
							<td>${vo.regDate}</td>
							<td><a href="${pageContext.request.contextPath}/guestbook/delete/${vo.id}">삭제</a></td>
						</tr>
						<tr>
							<td colspan=4>
								${fn:replace(vo.contents, newline, "<br>")}
							</td>
						</tr>
					</table>
				</c:forEach>
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>