package com.google.pacoavila;

import com.google.cloud.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "GetBlogPost", value="/blogpost")
public class GetBlogPost extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
    cache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    String id = req.getParameter("id");
    Key key = datastore.newKeyFactory().setKind(BlogPost.KIND_KEY).newKey(Long.valueOf(id));

    Entity e;
    Object cachedVal = cache.get(key.getId().toString());
    if (cachedVal == null) {
      e = datastore.get(key);
    } else {
      e = (Entity) cachedVal;
    }

    cache.put(key.getId().toString(), e);
    req.setAttribute("post", new BlogPost(e));
    req.getRequestDispatcher("post.jsp").forward(req, resp);
  }
}
