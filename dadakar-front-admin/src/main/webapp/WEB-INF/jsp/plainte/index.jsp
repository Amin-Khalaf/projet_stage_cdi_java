<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Dadakar - Administration - gestion des comptes utilisateur</title>
		<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
	</head>
	<body>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">DaDaKar</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/">Home</a></li>
						<li><a href="/user/index">Voir la liste des utilisateurs</a></li>
						<li class="active"><a href="/plainte/index">Voir la liste des plaintes</a></li>
						<li><a href="/price/index">Spécifier les prix et les taux de marge</a></li>
						<li class="disabled"><a href="/admin/index">Gérer les administrateurs</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="edit/${account.accountId}">Changer de mot de passe</a></li>
						<li><a href="../disconnect">Déconnexion</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="container-fluid">
			<c:if test="${not empty msg}">
				<div class="alert alert-${css} alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">x</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>
			<form:form method="POST" commandName="bannished" action="bannishall" >
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" id="bannishAll" /></th>
							<th>Banni</th>
							<th>Nom Prénom</th>
							<th>Nombre d'avis</th>
							<th>Pourcentage d'avis négatifs</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${complaints}" var="complaint" varStatus="itr">
							<tr>
								<td><form:checkbox path="banned" value="${complaint.user.account.accountId}" /></td>
								<td>${complaint.user.account.banned?'oui':'non'}</td>
								<td>${complaint.user.lastName} ${complaint.user.firstName}</td>
								<td>${complaint.numberOfRatings}</td>
								<td>${complaint.percentOfBadRatings}</td>
								<td><a  href="message/1234:${complaint.user.userId}" class="btn btn-warning">Message</a></td>
								<td><a href="bannish/${complaint.user.userId}" class="btn btn-danger" onclick="return confirm('Etes vous sur de vouloir ${complaint.user.account.banned?'réintégrer':'bannir'} cet utilisateur ?')">${complaint.user.account.banned?'Réintégrer':'Bannir'}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input class="btn btn-danger" type="submit" onclick="return confirm('Etes vous sur de vouloir bannir/réintégrer tout les utilisateus selectionnés ?')" value="Bannir/réintégrer tous" />
			</form:form>
		</div>		
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../js/function.js"></script>
	</body>
</html>