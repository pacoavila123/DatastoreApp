package com.google.pacoavila;

import com.google.cloud.datastore.Entity;
import javax.annotation.Nonnull;

public class BlogPost {

  static final String KIND_KEY = "blog-post";
  static final String TITLE_KEY = "title";
  static final String AUTHOR_KEY = "author";
  static final String DESCRIPTION_KEY = "description";

  private String id;
  private String title;
  private String author;
  private String description;

  public String getId() {
    return id;
  }
  public String getTitle() {
    return title;
  }
  public String getAuthor() {
    return author;
  }
  public String getDescription() {
    return description;
  }

  public BlogPost(@Nonnull Entity entity) {
    if (entity.hasKey()) {
      id = Long.toString(entity.getKey().getId());
    }
    title = entity.getString(TITLE_KEY);
    author = entity.getString(AUTHOR_KEY);
    description = entity.getString(DESCRIPTION_KEY);
  }
}
