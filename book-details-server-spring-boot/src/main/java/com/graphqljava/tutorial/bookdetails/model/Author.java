package com.graphqljava.tutorial.bookdetails.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Author {
  private String id;
  private String firstName;
  private String lastName;
}
