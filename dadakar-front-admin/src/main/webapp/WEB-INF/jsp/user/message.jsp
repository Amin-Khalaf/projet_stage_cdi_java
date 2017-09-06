<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Dadakar - Administration - Message utilisateur</title>
		<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
	</head>
	<body>
	
		<jsp:include page="../template/header.jsp"></jsp:include>
		
		<jsp:include page="../template/navbar.jsp">
			<jsp:param value="active" name="user-active"/>
			<jsp:param value="" name="complaint-active"/>
			<jsp:param value="" name="price-active"/>
			<jsp:param value="" name="admin-active"/>
			<jsp:param value="" name="password-active"/>
		</jsp:include>
		
		<form:form modelAttribute="message" method="POST" action="/user/send">
			<form:hidden path="senderId" />
			<form:hidden path="receiverId" />
			<form:hidden path="horo"/>
			<spring:bind path="object">
				<div class="form-group">
					<label class="col-sm-2 control-label">Objet :</label>
					<div class ="col-sm-10">
						<form:input path="object" class="form-control" id="object"/>
					</div>
				</div>
			</spring:bind>
			<spring:bind path="message">
				<div class="form-group">
					<label class="col-sm-2 control-label">Message :</label>
					<div class="col-sm-10">
						<form:textarea path="message" rows="10" class="form-control" id="message" />
					</div>
				</div>
			</spring:bind>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary btn-pull-right">Envoyer le Message</button>
				</div>
			</div>
		</form:form>
		
		<jsp:include page="../template/footer.jsp"></jsp:include>
		
	</body>
</html>