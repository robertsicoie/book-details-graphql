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
public class InputBook {
    private String id;
    private String name;
    private Integer pageCount;
    private InputAuthor author;
}
