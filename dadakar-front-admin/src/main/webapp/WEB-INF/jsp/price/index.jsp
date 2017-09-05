<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dadakar- Administration - Gestion des prix</title>
<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
<link type="text/css" href="/css/main.css" rel="stylesheet" />
</head>
<body class="col-xs-12">
	<header>
		<h1>Gestion des prix</h1>
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
					<li class="active"><a href="/price/index">Spécifier les
							prix et les taux de marge</a></li>
					<li><a href="/admin/index">Gérer les administrateurs</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="edit/${account.accountId}">Changer de mot de
							passe</a></li>
					<li><a href="../disconnect">Déconnexion</a></li>
				</ul>
			</div>
		</div>
	</nav>

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
		<c:when test="${empty runpriceForm.runPriceId}">
		<h2>Ajouter un prix</h2>
		</c:when>
		<c:otherwise>
				<h2>Modifier le prix</h2>
		</c:otherwise>
	</c:choose>
		<form:form action="/price/save" method="POST"
			modelAttribute="runpriceForm" class="form-inline">

			<form:input path="runPriceId" type="hidden" id="runPriceId"
				placeholder="runPriceId" />

			<spring:bind path="power">
				<div class="form-group ${status.error ? 'has-error' : ''} col-xs-4">
					<label class="control-label">Puissance fiscale du véhicule</label>
					<div>
						<form:input path="power" type="number" class="form-control"
							id="power" placeholder="Power" pattern="[0-9]{1,2}" />
						<br />
						<form:errors path="power" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="startingPrice">
				<div class="form-group ${status.error ? 'has-error' : ''} col-xs-4">
					<label class="control-label">Prix de base par km</label>
					<div>
						<form:input path="startingPrice" type="text" class="form-control"
							id="startingPrice" placeholder="StartingPrice"
							pattern="[0-9]{1,7}.{0,1}[0-9]{0,2}" />
						<br />
						<form:errors path="startingPrice" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="minPrice">
				<div class="form-group ${status.error ? 'has-error' : ''} col-xs-4">
					<label class="control-label">Prix minimum</label>
					<div>
						<form:input path="minPrice" type="text" class="form-control"
							id="minPrice" placeholder="MinPrice"
							pattern="[0-9]{1,7}.{0,1}[0-9]{0,2}" />
						<br />
						<form:errors path="minPrice" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="maxPrice">
				<div class="form-group ${status.error ? 'has-error' : ''} col-xs-4">
					<label class="control-label">Prix maximum</label>
					<div>
						<form:input path="maxPrice" type="text" class="form-control"
							id="maxPrice" placeholder="MaxPrice"
							pattern="[0-9]{1,7}.{0,1}[0-9]{0,2}" />
						<br />
						<form:errors path="maxPrice" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="rate">
				<div class="form-group ${status.error ? 'has-error' : ''} col-xs-4">
					<label class="control-label">Prix maximum</label>
					<div>
						<form:input path="rate" type="text" class="form-control" id="rate"
							placeholder="Rate" pattern="0.[0-9]{1,3}" />
						<br />
						<form:errors path="rate" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<div class="form-group col-xs-4">
				<label class="control-label"></label>
				<div>
					<a href="/price/index" class="btn btn-warning">Annuler</a> <input
						type="submit" value="enregistrer" class="btn btn-success" />
				</div>
			</div>
		</form:form>
	</section>

	<section class="col-xs-12">
		<h2>Liste des prix par km</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Puissance</th>
					<th>Prix</th>
					<th>Prix min</th>
					<th>Prix max</th>
					<th>Taux site</th>
					<th>Modifier</th>
					<th>Supprimer</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${prices}" var="price" varStatus="itr">
					<tr>
						<td>${price.power}</td>
						<td>${price.startingPrice}</td>
						<td>${price.minPrice}</td>
						<td>${price.maxPrice}</td>
						<td>${price.rate}</td>
						<c:choose>
							<c:when test="${not empty runpriceForm.runPriceId}">
								<td>
									<button class="btn btn-warning" disabled>Modifier</button>
								</td>
								<td>
									<button class="btn btn-danger" disabled>Supprimer</button>
								</td>
							</c:when>
							<c:otherwise>
								<td><a href="edit/${price.runPriceId}"
									class="btn btn-warning">Modifier</a></td>
								<td><a href="delete/${price.runPriceId}"
									class="btn btn-danger"
									onclick="return confirm('Voulez-vous vraiment supprimer cet enregistrement ?')">Supprimer</a></td>
							</c:otherwise>
						</c:choose>
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