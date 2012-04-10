<%@page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"></jsp:include>
<div class="hero-unit">
	<h1>Le président est ...</h1>
<%
BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
    <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
        <label>Nom du président : </label><input type="text" name="name" placeholder="le nom du candidat gagnant"/>
        <label>Photo du président : </label><input type="file" name="image"/>
        <div class="form-action">
        	<input type="submit" value="Envoyer" class="btn btn-primary"/>
        </div>
    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>