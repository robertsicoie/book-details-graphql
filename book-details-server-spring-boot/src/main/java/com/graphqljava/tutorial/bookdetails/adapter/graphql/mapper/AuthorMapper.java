package com.graphqljava.tutorial.bookdetails.adapter.graphql.mapper;

import com.graphqljava.tutorial.bookdetails.adapter.graphql.model.InputAuthor;
import com.graphqljava.tutorial.bookdetails.model.Author;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthorMapper {

  Author toModel(InputAuthor author);

}
