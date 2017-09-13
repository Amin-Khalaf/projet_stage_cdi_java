<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Insert title here</title>
		<link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet" />
		<link type="text/css" href="/css/main.css" rel="stylesheet" />
	</head>
	<body>
		<h1 class="text-success text-center">DaDaKar - Admin</h1>
	
		<c:if test="${param.error != null}">
            <div class="alert alert-danger">
                Nom d'utilisateur ou mot de passe incorrect.
            </div>
		</c:if>

		<c:if test="${param.logout != null}">
			<div class="alert alert-info">
                Vous êtes déconnecté.
            </div>
		</c:if>

		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<table class="container-fluid">
						<form:form method="POST" action="/login" modelAttribute="connectForm">
							<tr>
								<td><label class="control-label" for="username">Entrez votre nom d'utilisateur :&nbsp;</label></td>
								<td><input type="text" class="form-control" name="username"/><br></td>
							</tr>
							<tr>
								<td><label class="control-label" for="password">Entrez votre mot de passe :&nbsp;</label></td>
								<td><input type="password" name="password" class="form-control"></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit" class="btn btn-success" value="Connexion" /></td>
							</tr>
						</form:form>
					</table>
				</div>
			</div>
		</div>
	
		<jsp:include page="template/footer.jsp"></jsp:include>

	</body>
</html>