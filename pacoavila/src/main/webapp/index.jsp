<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.pacoavila.BlogPost" %>
<%@ page import="java.util.List" %>
<html>
  <head>

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="assets/css/clean-blog.css">

    <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
    <title>The Dog Blog</title>
  </head>

  <body>
    <!-- Page Header -->
    <header class="masthead" style="background-image: url('../assets/img/doge.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>Welcome to The Dog Blog!</h1>
              <span class="subheading">Your one stop shop for all things canine.</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <c:forEach items="${posts}" var="p">
            <div class="post-preview">
              <a href="post.html">
                <h2 class="post-title">
                  <c:out value="${p.getTitle()}"/>
                </h2>
                <h3 class="post-subtitle">
                  <c:out value="${p.getDescription()}"/>
                </h3>
              </a>
              <p class="post-meta">Posted by
                <a href="#"><c:out value="${p.getAuthor()}"/></a>
                on September 24, 2019</p>
            </div>
            <hr>
           </c:forEach>
          </div>
          <!-- Pager -->
          <div class="clearfix">
            <a class="btn btn-primary float-right" href="#">Older Posts &rarr;</a>
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
