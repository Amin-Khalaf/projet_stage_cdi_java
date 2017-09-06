<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Dadakar - Modifier le compte</title>
		<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
		<link type="text/css" href="/css/main.css" rel="stylesheet" />
	</head>
	<body class="col-xs-12">
		<header>
			<h1>Modifier le compte</h1>
		</header>
		
		<jsp:include page="../template/header.jsp"></jsp:include>
		
		<jsp:include page="../template/navbar.jsp">
			<jsp:param value="" name="user-active"/>
			<jsp:param value="" name="complaint-active"/>
			<jsp:param value="" name="price-active"/>
			<jsp:param value="active" name="admin-active"/>
			<jsp:param value="" name="password-active"/>
			<jsp:param value="" name="admin-enable"/>
			<jsp:param value="" name="adminId"/>
		</jsp:include>
		
		<section>
			<h2>Compte</h2>
			<form:form action="/admin/update" method="POST" modelAttribute="accountForm">
				<label for="username">Utilisateur : </label>
				<input type="text" name="username" value="${account.username}" />
				<br />
				<label for="password">Mot de passe : </label>
				<input type="password" name="password" value="${account.password}">
				<br />
				<label for="role">Role : </label>
				<select name="role" >
					<c:choose>
						<c:when test="${account.role == 'ADMIN'}">
							<option selected="selected" value="ADMIN">Administrateur</option>
							<option value="SUPERUSER">SuperUtilisateur</option>
						</c:when>
						<c:otherwise>
							<option value="ADMIN">Administrateur</option>
							<option selected="selected" value="SUPERUSER">SuperUtilisateur</option>
						</c:otherwise>
					</c:choose>
				</select>
				<br />
				<input type="submit" class="btn btn-success" value="Enregistrer" />
				<a href="/admin/index" class="btn btn-warning">Annuler</a>
			</form:form>
		</section>

		<jsp:include page="../template/footer.jsp"></jsp:include>

	</body>
</html>