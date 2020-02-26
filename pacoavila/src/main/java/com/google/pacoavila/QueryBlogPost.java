package com.google.pacoavila;

import com.google.api.client.util.Lists;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.api.utils.SystemProperty.Environment.Value;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "QueryBlogPost", value="/")
public class QueryBlogPost extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    List<BlogPost> posts = getPosts();
    req.setAttribute("posts", posts);
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

  public static List<BlogPost> getPosts() {

    // TODO(pacoavila) This can be removed if the local development server is running.
    if (SystemProperty.environment.value() == Value.Development) {
      return new ArrayList<>();
    }

    MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
    cache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    StructuredQuery<Entity> query = Query.newEntityQueryBuilder().setKind(BlogPost.KIND_KEY).build();
    QueryResults<Entity> results = datastore.run(query);
    List<BlogPost> posts = Lists.newArrayList();
    while (results.hasNext()) {
      Entity result = results.next();
      posts.add(new BlogPost(result));
    }
    return posts;
  }
}
