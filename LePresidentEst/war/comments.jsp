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
    if (user == null)
    {%>
        <p>Bonjour, <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">
        identifiez-vous</a> pour pouvoir commenter l'élection.</p>
    <%}
    else
    {
        String newcomment = request.getParameter("user-comment");
        if (newcomment != null) {
        	Comment.store(newcomment, user.getEmail());
        }
        for (Comment comment : Comment.retrieveAll()) {
            String commentext = comment.text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
%>
        <p><b><%=comment.user%>:</b> <%=commentext%></p>
    <% } %>
        <form action="" method="post">
            <textarea name="user-comment"></textarea><br/>
            <input type="submit" value="c'est mon avis" />
            <!-- lien de déconnexion -->
            <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">déconnexion</a>
        </form>
<% } %>
</div>
<jsp:include page="footer.jsp"></jsp:include>
