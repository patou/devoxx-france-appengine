<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="fr.devoxx.lepresidentest.entity.Counter"%>
<%@page import="fr.devoxx.lepresidentest.entity.President"%>
<jsp:include page="header.jsp"></jsp:include>
<div class="hero-unit">
  	<h1>Le président est ...</h1>
  	<%
  	President president = President.getPresident();
  	//Si le président est actif
  	if (president != null) {
  	%>
  	<img src="<%= president.image %>" />
  	<h2>
  	<%= president.name %>
	</h2>
  	<% } else { //Sinon affiche une image %>
  	<img src="presidents.png" />
  	<div class="alert span6">
			<a class="close" data-dismiss="alert">×</a>
			<strong>Le président ne sera annoncé qu'à partir du 6 mai 2012 à 20H00</strong>
	</div>
	<% } %>
	<div class="row">
  		<a class="btn btn-primary btn-large" href="/vote/+1">+1</a>
		<% //Calcule du compteur en récupérant la valeur "+1" - la valeur "-1" 
		out.print(Counter.value("+1") - Counter.value("-1")); %>
		<a class="btn btn-primary btn-large" href="/vote/-1">-1</a>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
