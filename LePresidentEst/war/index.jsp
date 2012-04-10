<%@page import="com.google.appengine.api.images.ImagesServiceFactory"%>
<%@page import="com.google.appengine.api.images.ImagesService"%>
<%@page import="fr.devoxx.lepresidentest.entity.President"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<jsp:include page="header.jsp"></jsp:include>
<div class="hero-unit">
  	<h1>Le président est ...</h1>
  	<%
  	President president = President.getPresident();
  	if (president != null) {
  		ImagesService service = ImagesServiceFactory.getImagesService();
  	%>
  	<img src="<%= service.getServingUrl(president.images) %>" />
  	<h2>
  	<%= president.name %>
	</h2>
  	<% } else { %>
  	<img src="presidents.png" />
  	<div class="alert span6">
			<a class="close" data-dismiss="alert">×</a>
			<strong>Le président ne sera annoncé qu'à partir du 6 mai 2012 à 20H00</strong>
	</div>
	<% } %>
	<div class="row">
  		<a class="btn btn-primary btn-large" href="/vote/+1">
				+1
		</a>
		<a class="btn btn-primary btn-large" href="/vote/-1">
				-1
		</a>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
