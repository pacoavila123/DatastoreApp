<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.pacoavila.TheDogBlog" %>
<%@ page import="com.google.pacoavila.BlogPost" %>
<%@ page import="com.google.api.client.util.Lists" %>
<%@ page import="java.util.List" %>
<html>
<head>
  <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
  <title>The Dog Blog</title>
</head>
<body>
    <h1>Welcome to The Dog Blog!</h1>

  <p><%= TheDogBlog.getInfo() %></p>
  <table>
    <tr>
      <td colspan="2" style="font-weight:bold;">Available Servlets:</td>
    </tr>
    <tr>
      <td><a href='/hello'>Hello Dog Blog</a></td>
      <td><a href='/create'>Post About Doggos</a></td>
    </tr>
  </table>

  <h2>Top Doggos</h2>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  <div class="container">
    <table>
      <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Description</th>
    </table>
    <c:forEach items="${posts}" var="p">
        <tr>
            <td><c:out value="${p.getTitle()}"/></td>
            <td><c:out value="${p.getAuthor()}"/></td>
            <td><c:out value="${p.getDescription()}"/></td>
        </tr>
    </c:forEach>
  </div>

</body>
</html>
