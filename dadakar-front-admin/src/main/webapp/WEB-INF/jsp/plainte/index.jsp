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

		<jsp:include page="../template/header.jsp"></jsp:include>
		
		<jsp:include page="../template/navbar.jsp">
			<jsp:param value="" name="user-active"/>
			<jsp:param value="active" name="complaint-active"/>
			<jsp:param value="" name="price-active"/>
			<jsp:param value="" name="admin-active"/>
			<jsp:param value="" name="password-active"/>
			<jsp:param value="" name="admin-enable"/>
			<jsp:param value="" name="adminId"/>
		</jsp:include>
		
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

		<jsp:include page="../template/footer.jsp"></jsp:include>
		
	</body>
</html>