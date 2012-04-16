<%@page import="fr.devoxx.lepresidentest.entity.Comment"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="com.google.appengine.api.users.UserService"%>

<jsp:include page="header.jsp"></jsp:include>
<div class="hero-unit">
<%
 UserService userService = UserServiceFactory.getUserService();
 User user = userService.getCurrentUser();
//Ajouter un nouveau commentaire
String newcomment = request.getParameter("user-comment"); //Récupère le 
if (newcomment != null && !newcomment.isEmpty()) {
	Comment.store(newcomment, user.getNickname());
}
for (Comment comment : Comment.retrieveAll()) {
	//On échape les caractères 
    String commentext = comment.text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
%>
<p><b><%=comment.user%> :</b> <%=commentext%></p>
<% }
//Si on est pas connecté, affiche un lien pour se connecter
    if (user == null) 
    {%>
        <p><a href="<%= userService.createLoginURL(request.getRequestURI()) %>">
        Identifiez-vous</a> pour pouvoir commenter l'élection.</p>
    <%}
    else
    { //Sinon affiche le formulaire
%>
        <form action="" method="post">
            <textarea name="user-comment"></textarea><br/>
            <input type="submit" value="c'est mon avis" />
            <!-- lien de déconnexion -->
        </form>
        <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Déconnexion</a>
<% } %>
<p>Ou envoyez un mail à <a href="mailto:commentaires@electionfr2012.appspotmail.com?subject=Commentaire">commentaires@electionfr2012.appspotmail.com</a></p>
</div>
<jsp:include page="footer.jsp"></jsp:include>
