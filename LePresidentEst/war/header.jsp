<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<!DOCTYPE HTML>
<html lang="fr">
  <head>    
  	<meta charset="utf-8">
    <title>Le président est ...</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>

  <body>
	 <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/">Le président est</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li class="active">
                <a href="/">Accueil</a>
              </li>
              <li class="">
                <a href="/comments.jsp">Commentaires</a>
              </li>
              <li class="divider-vertical"></li>
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
%>
              <li class="">
                <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Déconnexion</a>
              </li>
<%
    	if (userService.isUserAdmin()) {
%>
              <li class="">
                <a href="/admin.jsp">Administration</a>
              </li>
<%
    	}
    }
    else {
%>
              <li class="">
                <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Connexion</a>
              </li>
<%
    	}
%>

            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
