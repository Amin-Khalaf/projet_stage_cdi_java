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
		<title>Dadakar- Administration - Modification d'un prix</title>
		<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
		<link type="text/css" href="/css/main.css" rel="stylesheet" />
	</head>
	<body class="col-xs-12">
		<header>
			<h1>Gestion des prix</h1>
		</header>
		
		<jsp:include page="../template/header.jsp"></jsp:include>
		
		<jsp:include page="../template/navbar.jsp">
			<jsp:param value="" name="user-active"/>
			<jsp:param value="" name="complaint-active"/>
			<jsp:param value="active" name="price-active"/>
			<jsp:param value="" name="admin-active"/>
			<jsp:param value="" name="password-active"/>
		</jsp:include>
		
		<section>
			<h2>Modifier un prix</h2>
			<form:form action="save" method="POST" modelAttribute="runpriceForm" class="form-horizontal">
				<form:hidden path="runPriceId" />
	
				<spring:bind path="power">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="col-sm-2 control-label">Puissance fiscale du
							véhicule</label>
						<div class="col-sm-5">
							<form:input path="power" type="number" class="form-control"
								id="power" placeholder="Power" pattern="[0-9]{1,2}" />
							<form:errors path="power" class="control-label" />
						</div>
					</div>
				</spring:bind>
	
				<spring:bind path="startingPrice">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="col-sm-2 control-label">Prix de base par km</label>
						<div class="col-sm-5">
							<form:input path="startingPrice" type="text" class="form-control"
								id="startingPrice" placeholder="StartingPrice"
								pattern="[0-9]{1,7}.{0,1}[0-9]{0,2}" />
							<form:errors path="startingPrice" class="control-label" />
						</div>
					</div>
				</spring:bind>
	
				<spring:bind path="minPrice">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="col-sm-2 control-label">Prix minimum</label>
						<div class="col-sm-5">
							<form:input path="minPrice" type="text" class="form-control"
								id="minPrice" placeholder="MinPrice"
								pattern="[0-9]{1,7}.{0,1}[0-9]{0,2}" />
							<form:errors path="minPrice" class="control-label" />
						</div>
					</div>
				</spring:bind>
	
				<spring:bind path="maxPrice">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="col-sm-2 control-label">Prix maximum</label>
						<div class="col-sm-5">
							<form:input path="maxPrice" type="text" class="form-control"
								id="maxPrice" placeholder="MaxPrice"
								pattern="[0-9]{1,7}.{0,1}[0-9]{0,2}" />
							<form:errors path="maxPrice" class="control-label" />
						</div>
					</div>
				</spring:bind>
	
				<spring:bind path="rate">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="col-sm-2 control-label">Prix maximum</label>
						<div class="col-sm-5">
							<form:input path="rate" type="text" class="form-control" id="rate"
								placeholder="Rate" pattern="0.[0-9]{1,3}" />
							<form:errors path="rate" class="control-label" />
						</div>
					</div>
				</spring:bind>
				<div class="form-group">
					<a href="/price/index" class="btn btn-warning">Annuler</a> 
					<input type="submit" value="Enregistrer" class="btn btn-success" />
				</div>
			</form:form>
		</section>
		
		<jsp:include page="../template/footer.jsp"></jsp:include>

	</body>
</html>