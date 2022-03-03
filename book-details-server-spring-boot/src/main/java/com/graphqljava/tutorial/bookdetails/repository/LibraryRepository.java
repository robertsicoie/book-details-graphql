package com.graphqljava.tutorial.bookdetails.repository;

import com.graphqljava.tutorial.bookdetails.model.Author;
import com.graphqljava.tutorial.bookdetails.model.Book;
import java.util.List;

public interface LibraryRepository {
  Book getBookById(String id);

  List<Book> getBooksByName(String name);

  List<Book> getAllBooks();

  Book addBook(Book book);

  Author getAuthorById(String id);

  List<Author> getAllAuthors();

  Author addAuthor(Author author);

  Author updateAuthor(Author existingAuthor);

  Book updateBook(Book book);
}
