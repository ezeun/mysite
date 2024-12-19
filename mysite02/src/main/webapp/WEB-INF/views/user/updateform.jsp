<%@page import="mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%= request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
<%
UserVo vo = (UserVo) request.getAttribute("vo");
%>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="updateForm" method="post" action="<%= request.getContextPath() %>/user">
					<input type='hidden' name='a' value="update">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="<%= vo.getName()%>">

					<label class="block-label" for="email">이메일</label>
					<h4><%= vo.getEmail()%></h4>
					
					<label class="block-label">패스워드</label> <!-- 수정안하면 이름이랑 성별만 업데이트 -->
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<%
						if(vo.getGender().equals("male")){
						%>
							<label>여</label> <input type="radio" name="gender" value="female">
							<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
						<%
						} else{
						%>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						<%
						}
						%>
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>