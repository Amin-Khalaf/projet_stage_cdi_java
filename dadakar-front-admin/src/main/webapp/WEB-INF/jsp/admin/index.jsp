<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dadakar - Administration - gestion compte admin</title>
	<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
	<link type="text/css" href="/css/main.css" rel="stylesheet" />
</head>
<body class="col-xs-12">
	<header>
		<h1>Gestion des comptes administrateurs</h1>
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
		<h2>Nouvel utilisateur</h2>
		<form:form action="create" method="POST" modelAttribute="accountForm">
			<label for="username">Utilisateur : </label>
			<input type="text" name="username" />
			<label for="password">Mot de passe : </label>
			<input type="password" name="password">
			<label for="role">Role : </label>
			<select name="role">
				<option value="ADMIN">Administrateur</option>
				<option value="SUPERUSER">SuperUtilisateur</option>
			</select>
			<input type="submit" class="btn btn-success" value="Créer" />
		</form:form>
	</section>
	<section>
		<h2>Liste des utilisateurs</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Utilisateur</th>
					<th>Rôle</th>
					<th>Modifier</th>
					<th>Supprimer</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${adminAccounts}" var="account" varStatus="itr">
					<tr>
						<td>${account.username}</td>
						<td>${account.role}</td>
						<td><c:if test="${account.deleted == false}">
								<a href="edit/${account.accountId }" class="btn btn-warning">Modifier</a>
							</c:if></td>
						<td><c:choose>
								<c:when test="${account.deleted}">
							        Supprimé
							    </c:when>
								<c:otherwise>
									<a href="delete/${account.accountId }" class="btn btn-danger" onclick="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?')">Supprimer</a>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	<footer> add footer </footer>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>