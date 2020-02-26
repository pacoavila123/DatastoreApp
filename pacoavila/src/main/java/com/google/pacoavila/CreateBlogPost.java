package com.google.pacoavila;

import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Imports the Google Cloud client library
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

@SuppressWarnings("serial")
@WebServlet(name = "CreateBlogPost", value="/create")
public class CreateBlogPost extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.getRequestDispatcher("createBlogPost.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    String title = req.getParameter(BlogPost.TITLE_KEY);
    String author = req.getParameter(BlogPost.AUTHOR_KEY);
    String description = req.getParameter(BlogPost.DESCRIPTION_KEY);

    // Prepares the new entity
    IncompleteKey incompleteKey = datastore.newKeyFactory().setKind(BlogPost.KIND_KEY).newKey();
    FullEntity post = FullEntity.newBuilder(incompleteKey)
        .set(BlogPost.TITLE_KEY, title)
        .set(BlogPost.AUTHOR_KEY, author)
        .set(BlogPost.DESCRIPTION_KEY, description)
        .build();

    Entity postWithId = datastore.add(post);

    System.out.printf("Saved %s: %s%n", postWithId.getString(BlogPost.TITLE_KEY), postWithId.getString(BlogPost.DESCRIPTION_KEY));

    resp.sendRedirect("");
  }
}