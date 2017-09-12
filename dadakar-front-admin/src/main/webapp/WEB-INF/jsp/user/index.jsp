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
	
		<jsp:include page="../template/navbar.jsp">
		
			<jsp:param value="active" name="user-active"/>
			<jsp:param value="" name="complaint-active"/>
			<jsp:param value="" name="price-active"/>
			<jsp:param value="" name="admin-active"/>
			<jsp:param value="" name="password-active"/>
			<jsp:param value="" name="admin-enable"/>
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
								<td><a  href="message/<%= request.getSession().getAttribute("uid")%>:${usr2.userId}" class="btn btn-warning">Message</a></td>
								<td><a href="bannish/${usr2.userId}" class="btn btn-danger" onclick="return confirm('Etes vous sur de vouloir ${usr2.account.banned?'réintégrer':'bannir'} cet utilisateur ?')">${usr2.account.banned?'Réintégrer':'Bannir'}</a></td>
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