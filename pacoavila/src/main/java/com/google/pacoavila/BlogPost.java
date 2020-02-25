package com.google.pacoavila;

import com.google.cloud.datastore.Entity;

public class BlogPost {

  static final String KIND_KEY = "Task";
  static final String TITLE_KEY = "title";
  static final String AUTHOR_KEY = "author";
  static final String DESCRIPTION_KEY = "description";

  private String title;
  private String author;
  private String description;

  public String getTitle() {
    return title;
  }
  public String getAuthor() {
    return author;
  }
  public String getDescription() {
    return description;
  }

  public BlogPost(Entity entity) {
    title = entity.getString(TITLE_KEY);
    author = entity.getString(AUTHOR_KEY);
    description = entity.getString(DESCRIPTION_KEY);
  }
}
