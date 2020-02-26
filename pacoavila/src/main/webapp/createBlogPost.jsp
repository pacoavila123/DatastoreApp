<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
  <title>Post about Doggos</title>
</head>
<body>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
      <h2>
        Put your dog post here! Note: Web dev doggin in progress...
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
    </div>
  </div>
</body>
</html>
