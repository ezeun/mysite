<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="updateForm" method="post" action="${pageContext.request.contextPath}/user/update">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${vo.name }">

					<label class="block-label" for="email">이메일</label>
					<h4>${vo.email }</h4>
					
					<label class="block-label">패스워드</label> <!-- 수정안하면 이름이랑 성별만 업데이트 -->
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						
						<c:choose>
							<c:when test='${vo.gender eq "male"  }'>
								<label>여</label> <input type="radio" name="gender" value="female">
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:when>
							<c:when test='${vo.gender eq "female"  }'>
								<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="male">
							</c:when>
						</c:choose>
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>