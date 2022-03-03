package com.graphqljava.tutorial.bookdetails.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {
  private String id;
  private String name;
  private Integer pageCount;
  private String authorId;
}
