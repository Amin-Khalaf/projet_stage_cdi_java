<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Dadakar - Modifier le compte</title>
	<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="col-xs-12">
	<header>
		<h1>Modifier le compte</h1>
	</header>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">DaDaKar</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/">Home</a></li>
					<li><a href="/user/">Voir la liste des utilisateurs</a></li>
					<li><a href="/plainte/index">Voir la liste des plaintes</a></li>
					<li><a href="/price/index">Spécifier les prix et les taux
							de marge</a></li>
					<li class="active"><a href="/admin/index">Gérer les
							administrateurs</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="edit/${account.accountId}">Changer de mot de
							passe</a></li>
					<li><a href="../disconnect">Déconnexion</a></li>
				</ul>
			</div>
		</div>
	</nav>
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
	<footer> add footer </footer>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>