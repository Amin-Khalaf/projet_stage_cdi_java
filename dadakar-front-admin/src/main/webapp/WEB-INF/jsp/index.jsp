<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Insert title here</title>
		<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
	</head>
	<body>
		<h1 class="text-success text-center">DaDaKar - Admin</h1>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<table class="container-fluid">
						<form:form method="POST" action="/connect" modelAttribute="connectForm">
							<tr>
								<td><label for="login">Entrez votre nom d'utilisateur :&nbsp;</label></td>
								<td><input type="text" name="login"/><br></td>
							</tr>
							<tr>
								<td><label for="pwd">Entrez votre mot de passe :&nbsp;</label></td>
								<td><input type="password"></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit" value="Connexion" /></td>
							</tr>
						</form:form>
					</table>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
	</body>
</html>