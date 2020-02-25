package com.google.pacoavila;

import com.google.api.client.util.Lists;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
    // PrintWriter out = resp.getWriter();
    // for (BlogPost post : posts) {
    //   out.printf("Retrieved %s: %s by %s%n", post.title, post.description, post.author);
    // }
  }

  public static List<BlogPost> getPosts() {
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
