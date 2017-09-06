<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">DaDaKar</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="/">Home</a></li>
				<li class=<%= request.getParameter("user-active") %>><a href="/user/index">Voir la liste des utilisateurs</a></li>
				<li class=<%= request.getParameter("complaint-active") %>><a href="/plainte/index">Voir la liste des plaintes</a></li>
				<li class=<%= request.getParameter("price-active") %>><a href="/price/index">Spécifier les prix et les taux
						de marge</a></li>
				<li class=<%= request.getParameter("admin-active") %>><a href="/admin/index">Gérer les
						administrateurs</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class=<%= request.getParameter("password-active") %>><a href="/admin/passwordchange">Changer de mot de passe</a></li>
				<li><a href="../disconnect">Déconnexion</a></li>
			</ul>
		</div>
	</div>
</nav>
