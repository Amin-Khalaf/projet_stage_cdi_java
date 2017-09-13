<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Dadakar - Administration - gestion de compte administrateur</title>
		<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
		<link type="text/css" href="/css/main.css" rel="stylesheet" />
	</head>
	<body class="col-xs-12">
		<jsp:include page="../template/navbar.jsp">
			<jsp:param value="" name="user-active"/>
			<jsp:param value="" name="complaint-active"/>
			<jsp:param value="" name="price-active"/>
			<jsp:param value="active" name="admin-active"/>
			<jsp:param value="" name="password-active"/>
			<jsp:param value="" name="admin-enable"/>
		</jsp:include>

		<h1 class="text-success">Gestion des comptes administrateurs</h1>
		
		<c:if test="${not empty message}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${message}</strong>
			</div>
		</c:if>
	
		<section class="col-xs-12">
			<c:choose>
				<c:when test="${empty accountForm.accountId}">
					<h2>Nouvel utilisateur</h2>
				</c:when>
				<c:otherwise>
					<h2>Modifier l'utilisateur</h2>
				</c:otherwise>
			</c:choose>
			<form:form action="/admin/save" method="POST"
				modelAttribute="accountForm" class="form-inline">
	
				<form:input path="accountId" type="hidden" id="accountId"
					placeholder="AccountId" />
	
				<spring:bind path="username">
					<div class="form-group ${status.error ? 'has-error' : ''} col-xs-4">
						<label for="username" class="control-label">Nom d'utilisateur : </label>
						<div>
							<form:input path="username" type="text" class="form-control"
								id="username" placeholder="Username" />
							<br />
							<form:errors path="username" class="control-label" />
						</div>
					</div>
				</spring:bind>
	
				<spring:bind path="password">
					<div class="form-group ${status.error ? 'has-error' : ''} col-xs-4">
						<label for="password" class="control-label">Mot de passe : </label>
						<div>
							<form:input path="password" type="text" class="form-control"
								id="password" placeholder="Password" />
							<br />
							<form:errors path="password" class="control-label" />
						</div>
					</div>
				</spring:bind>
	
				<spring:bind path="role">
					<div class="form-group ${status.error ? 'has-error' : ''} col-xs-4">
						<label for="role" class="control-label">Rôle</label>
						<div>
							<form:select path="role" class="form-control" id="role"
								placeholder="role">
								<form:option value="ADMIN">Administrateur</form:option>
								<form:option value="SUPERUSER">SuperUtilisateur</form:option>
							</form:select>
							<br />
							<form:errors path="role" class="control-label" />
						</div>
					</div>
				</spring:bind>
	
				<div class="form-group col-xs-4">
					<label class="control-label"></label>
					<div>
						<a href="/admin/index" class="btn btn-warning">Annuler</a> <input
							type="submit" class="btn btn-success" value="Enregistrer" />
					</div>
				</div>
			</form:form>
		</section>
	
		<section class="col-xs-12">
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
							<c:choose>
								<c:when test="${account.deleted}">
								       <td></td>
								       <td>Supprimé</td>
								</c:when>
								<c:when test="${empty accountForm.accountId}">
									<td>
										<a href="edit/${account.accountId }" class="btn btn-warning">Modifier</a>
									</td>
									<td>
										<a href="delete/${account.accountId }" class="btn btn-danger"
											onclick="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?')">Supprimer</a>
									</td>
								</c:when>
								<c:otherwise>
	 								<!-- in update mode, buttons are disabled -->
									<td>
										<button class="btn btn-warning" disabled>Modifier</button>
									</td>
									<td>
										<button class="btn btn-danger" disabled>Supprimer</button>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	
		<jsp:include page="../template/footer.jsp"></jsp:include>

	</body>
</html>