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

  private static final String TITLE_KEY = "title";
  private static final String AUTHOR_KEY = "author";
  private static final String DESCRIPTION_KEY = "description";

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    String kind = "Task";
    String title = req.getParameter(TITLE_KEY);
    String author = req.getParameter(AUTHOR_KEY);
    String description = req.getParameter(DESCRIPTION_KEY);

    // The Cloud Datastore key for the new entity
    Key taskKey = datastore.newKeyFactory().setKind(kind).newKey(title);

    // Prepares the new entity
    Entity task = Entity.newBuilder(taskKey)
        .set(DESCRIPTION_KEY, description)
        .build();

    // Saves the entity
    datastore.put(task);

    System.out.printf("Saved %s: %s%n", task.getKey().getName(), task.getString(DESCRIPTION_KEY));

    //Retrieve entity
    Entity retrieved = datastore.get(taskKey);

    PrintWriter out = resp.getWriter();
    out.printf("Retrieved %s: %s%n", taskKey.getName(), retrieved.getString(DESCRIPTION_KEY));
  }
}