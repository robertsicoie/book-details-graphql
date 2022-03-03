package com.graphqljava.tutorial.bookdetails.adapter.graphql.mapper;

import com.graphqljava.tutorial.bookdetails.adapter.graphql.model.InputBook;
import com.graphqljava.tutorial.bookdetails.model.Book;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface BookMapper {

  Book toModel(InputBook inputBook);

}
