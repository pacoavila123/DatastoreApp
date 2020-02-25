<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.pacoavila.HelloAppEngine" %>
<%@ page import="com.google.pacoavila.BlogPost" %>
<%@ page import="com.google.api.client.util.Lists" %>
<%@ page import="java.util.List" %>
<html>
<head>
  <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
  <title>Hello App Engine Standard Java 8</title>
</head>
<body>
    <h1>Hello App Engine -- Java 8!</h1>

  <p>This is <%= HelloAppEngine.getInfo() %>.</p>
  <table>
    <tr>
      <td colspan="2" style="font-weight:bold;">Available Servlets:</td>
    </tr>
    <tr>
      <td><a href='/hello'>Hello App Engine</a></td>
      <td><a href='/create'>Create Blog Post</a></td>
    </tr>
  </table>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  <div class="container">
    <c:forEach items="${posts}" var="p">
        <tr>
            <td>Title: <c:out value="${p.title}"/></td>
            <td>Author: <c:out value="${p.author}"/></td>
            <td>Description: <c:out value="${p.description}"/></td>
        </tr>
    </c:forEach>
  </div>

</body>
</html>
