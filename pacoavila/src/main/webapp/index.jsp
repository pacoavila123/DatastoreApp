<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.pacoavila.HelloAppEngine" %>
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
    </tr>
  </table>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  <div class="container">
    <h2>
      Create a new blog post
    </h2>

    <form method="POST" action="/create">

      <div>
        <label for="title">Title</label>
        <input type="text" name="title" id="title" size="40" value="${fn:escapeXml(blog.title)}" class="form-control" />
      </div>

      <div>
        <label for="author">Author</label>
        <input type="text" name="author" id="author" size="40" value="${fn:escapeXml(blog.author)}" class="form-control" />
      </div>

      <div>
        <label for="description">Post content</label>
        <textarea name="description" id="description" rows="10" cols="50" class="form-control">${fn:escapeXml(blog.content)}</textarea>
      </div>

      <button type="submit">Save</button>
    </form>
  </div>

</body>
</html>
