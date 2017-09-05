<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Dadakar - Administration - Message utilisateur</title>
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
		<form:form modelAttribute="message" method="POST" action="/user/send">
			<form:hidden path="senderId" />
			<form:hidden path="receiverId" />
			<form:hidden path="horo"/>
			<spring:bind path="object">
				<div class="form-group">
					<label class="col-sm-2 control-label">Objet :</label>
					<div class ="col-sm-10">
						<form:input path="object" class="form-control" id="object"/>
					</div>
				</div>
			</spring:bind>
			<spring:bind path="message">
				<div class="form-group">
					<label class="col-sm-2 control-label">Message :</label>
					<div class="col-sm-10">
						<form:textarea path="message" rows="10" class="form-control" id="message" />
					</div>
				</div>
			</spring:bind>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary btn-pull-right">Envoyer le Message</button>
				</div>
			</div>
		</form:form>
		
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	</body>
</html>