package com.google.pacoavila;

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

    String kind = "Task";
    String title = req.getParameter(BlogPost.TITLE_KEY);
    String author = req.getParameter(BlogPost.AUTHOR_KEY);
    String description = req.getParameter(BlogPost.DESCRIPTION_KEY);

    // The Cloud Datastore key for the new entitytitle
    Key taskKey = datastore.newKeyFactory().setKind(kind).newKey(title);

    // Prepares the new entity
    Entity task = Entity.newBuilder(taskKey)
        .set(BlogPost.TITLE_KEY, title)
        .set(BlogPost.AUTHOR_KEY, author)
        .set(BlogPost.DESCRIPTION_KEY, description)
        .build();

    // Saves the entity
    datastore.put(task);

    System.out.printf("Saved %s: %s%n", task.getString(BlogPost.TITLE_KEY), task.getString(BlogPost.DESCRIPTION_KEY));

    //Retrieve entity
    Entity retrieved = datastore.get(taskKey);

    PrintWriter out = resp.getWriter();
    out.printf("Retrieved %s: %s%n", taskKey.getName(), retrieved.getString(BlogPost.DESCRIPTION_KEY));
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }
}