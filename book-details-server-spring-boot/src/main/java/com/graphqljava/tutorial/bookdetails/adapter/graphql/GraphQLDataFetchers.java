package com.graphqljava.tutorial.bookdetails.adapter.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphqljava.tutorial.bookdetails.adapter.graphql.mapper.AuthorMapper;
import com.graphqljava.tutorial.bookdetails.adapter.graphql.mapper.BookMapper;
import com.graphqljava.tutorial.bookdetails.adapter.graphql.model.InputAuthor;
import com.graphqljava.tutorial.bookdetails.model.Author;
import com.graphqljava.tutorial.bookdetails.model.Book;
import com.graphqljava.tutorial.bookdetails.adapter.graphql.model.InputBook;
import com.graphqljava.tutorial.bookdetails.repository.LibraryRepository;
import graphql.schema.DataFetcher;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {

    @Autowired
    private LibraryRepository repository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorMapper authorMapper;

    public DataFetcher getBookDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            String bookName = dataFetchingEnvironment.getArgument("name");
            if (bookId != null) {
                return Collections.singletonList(repository.getBookById(bookId));
            } else if (bookName != null) {
                return repository.getBooksByName(bookName);
            } else {
                return repository.getAllBooks();
            }
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            if (book != null) {
                return repository.getAuthorById(book.getAuthorId());
            } else {
                return repository.getAllAuthors();
            }
        };
    }

    public DataFetcher<Book> addBookDataFetcher() {
        return dataFetchingEnvironment -> {
            ObjectMapper mapper = new ObjectMapper();
            final InputBook inputBook = mapper.convertValue(dataFetchingEnvironment.getArguments().get("book"), InputBook.class);
            final Book book = bookMapper.toModel(inputBook);
            if (inputBook.getAuthor() != null) {
                final Author author = authorMapper.toModel(inputBook.getAuthor());
                if (author.getId() != null) {
                    if (repository.getAuthorById(author.getId()) != null) {
                        book.setAuthorId(author.getId());
                    }
                } else {
                    final Author addedAuthor = repository.addAuthor(author);
                    book.setAuthorId(addedAuthor.getId());
                }
            }
            return repository.addBook(book);
        };
    }

    public DataFetcher<Author> addAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            ObjectMapper mapper = new ObjectMapper();
            final InputAuthor inputAuthor = mapper.convertValue(dataFetchingEnvironment.getArguments().get("author"), InputAuthor.class);
            return repository.addAuthor(authorMapper.toModel(inputAuthor));
        };
    }

    public DataFetcher<Author> updateAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            ObjectMapper mapper = new ObjectMapper();
            final InputAuthor inputAuthor = mapper.convertValue(dataFetchingEnvironment.getArguments().get("author"), InputAuthor.class);
            final Author author = authorMapper.toModel(inputAuthor);
            return repository.updateAuthor(author);
        };
    }


    public DataFetcher<Book> updateBookDataFetcher() {
        return dataFetchingEnvironment -> {
            ObjectMapper mapper = new ObjectMapper();
            final InputBook inputBook = mapper.convertValue(dataFetchingEnvironment.getArguments().get("book"), InputBook.class);
            final Book book = bookMapper.toModel(inputBook);
            if (inputBook.getAuthor() != null) {
                final Author author = authorMapper.toModel(inputBook.getAuthor());
                author.setId(repository.getBookById(book.getId()).getAuthorId());
                repository.updateAuthor(author);
            }

            return repository.updateBook(book);
        };
    }
}
