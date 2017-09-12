<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Dadakar - Changer de mot de passe</title>
		<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
		<link type="text/css" href="/css/main.css" rel="stylesheet" />
	</head>
	<body class="col-xs-12">
		<jsp:include page="../template/header.jsp"></jsp:include>
		
		<jsp:include page="../template/navbar.jsp">
			<jsp:param value="" name="user-active"/>
			<jsp:param value="" name="complaint-active"/>
			<jsp:param value="" name="price-active"/>
			<jsp:param value="" name="admin-active"/>
			<jsp:param value="active" name="password-active"/>
			<jsp:param value="" name="admin-enable"/>
		</jsp:include>
		
		<c:if test="${not empty message}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${message}</strong>
			</div>
		</c:if>
	
		<section>
			<h2>Compte</h2>
			<form:form action="/admin/changepassword" method="POST" modelAttribute="passChangeForm">
				<form:hidden path="accountId"/>
				<form:hidden path="url"/>
				<spring:bind path="oldPassword">
					<div class="form-group">
						<label class="col-sm-2 control-label">Ancien mot de passe</label>
						<div class="col-sm-10">
							<form:password path="oldPassword" class="form-control" id="oldPassword"/>
							<form:errors path ="oldPassword" class="control-label"/>
						</div>
					</div>
				</spring:bind>
				<spring:bind path="newPassword">
					<div class="form-group">
						<label class="col-sm-2 control-label">Ancien mot de passe</label>
						<div class="col-sm-10">
							<form:password path="newPassword" class="form-control" id="newPassword"/>
							<form:errors path ="newPassword" class="control-label"/>
						</div>
					</div>
				</spring:bind>
				<spring:bind path="confirmPassword">
					<div class="form-group">
						<label class="col-sm-2 control-label">Ancien mot de passe</label>
						<div class="col-sm-10">
							<form:password path="confirmPassword" class="form-control" id="confirmPassword"/>
							<form:errors path ="confirmPassword" class="control-label"/>
						</div>
					</div>
				</spring:bind>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-2">
						<button type="submit" class="btn btn-success">Confirmer le nouveau mot de passe</button>
					</div>
					<div class="col-sm-2">
						<a href="/admin/index" class="btn btn-warning">Annuler</a>
					</div>
				</div>
			</form:form>
		</section>
		
		<jsp:include page="../template/footer.jsp"></jsp:include>
		
	</body>
</html>