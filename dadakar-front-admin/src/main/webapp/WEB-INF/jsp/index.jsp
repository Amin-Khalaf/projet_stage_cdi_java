<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Insert title here</title>
		<link type="text/css" href="/css/bootstrap.css" rel="stylesheet" />
	</head>
	<body>
		<form:form method="POST" action="/connect" modelAttribute="connectForm">
			<label for="login">Entrez votre nom d'utilisateur</label>
			<input type="text" name="login"/>
			<label for="pwd">Entrez votre mot de passe</label>
			<input type="password">
			<input type="submit" value="Connexion" />
		</form:form>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
	</body>
</html>