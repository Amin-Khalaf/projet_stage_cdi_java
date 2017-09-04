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
						<li class="active"><a href="#user">Voir la liste des utilisateurs</a></li>
						<li><a href="/plainte/index">Voir la liste des plaintes</a></li>
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
		<form:form method="POST" commandName="bannished" action="bannishall" >
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><input type="checkbox" id="bannishAll" /></th>
						<th>Banni</th>
						<th>Nom</th>
						<th>Prénom</th>
						<th>Téléphone<br>(username)</th>
						<th>mail</th>
						<th>photo</th>
						<th>carte d'identité</th>
						<th>permis de conduire</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="usr2" varStatus="itr">
						<tr>
							<td><form:checkbox path="banned" value="${usr2.account.accountId}" /></td>
							<td>${usr2.account.banned?'oui':'non'}</td>
							<td>${usr2.lastName}</td>
							<td>${usr2.firstName}</td>
							<td>${usr2.account.username}</td>
							<td>${usr2.mail}</td>
							<td><a href="${imgPath}/name:user1.png" target="_blank"><img src="${imgPath}/name:user1.png" width="150 px" height="150 px"></a></td>
							<td><img src="${imgPath}/name:utilisateur2.jpg" width="150 px" height="150 px"></td>
							<td><img src="${imgPath}/name:voiture1.jpg" width="150 px" height="150 px"></td>
							<td><a  href="message/${usr2.userId}" class="btn btn-warning">Message</a></td>
							<td><a href="bannish/${usr2.userId}" class="btn btn-danger" onclick="return confirm('Etes vous sur de vouloir bannir/réintégrer cet utilisateur ?')">Bannir/réintégrer</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="submit" onclick="return confirm('Etes vous sur de vouloir bannir/réintégrer tout les utilisateus selectionnés ?')" value="Bannir/réintégrer tous" />
		</form:form>
		
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../js/function.js"></script>
	</body>
</html>