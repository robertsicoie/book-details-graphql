package com.graphqljava.tutorial.bookdetails.adapter.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InputAuthor {
  private String id;
  private String firstName;
  private String lastName;
}
