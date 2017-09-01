<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dadakar - Administration - gestion compte admin</title>
</head>
<body>
	<header>
		<h1>Gestion des comptes administrateurs</h1>
	</header>
	<menu>
		add menu
	</menu>
	<section>
		<table>
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
						<td><a href="" class="btn btn-warning">Modifier</a></td>
						<td><a href="" class="btn btn-danger">Supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	<footer>
		add footer
	</footer>
</body>
</html>