package com.graphqljava.tutorial.bookdetails.repository;

import com.graphqljava.tutorial.bookdetails.model.Author;
import com.graphqljava.tutorial.bookdetails.model.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InMemoryLibraryRepository implements LibraryRepository {

  private List<Book> books = new ArrayList<>();

  private List<Author> authors = new ArrayList<>();


  @Override
  public Book getBookById(String id) {
    return books
        .stream()
        .filter(book -> book.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public List<Book> getBooksByName(String name) {
    return books
        .stream()
        .filter(book -> book.getName().equals(name))
        .collect(Collectors.toList());
  }

  @Override
  public List<Book> getAllBooks() {
    return books;
  }

  @Override
  public Book addBook(Book book) {
    String bookId = "book-" + books.size();
    book.setId(bookId);
    books.add(book);
    return book;
  }

  @Override
  public Author getAuthorById(String id) {
    return authors
        .stream()
        .filter(author -> author.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public List<Author> getAllAuthors() {
    return authors;
  }

  @Override
  public Author addAuthor(Author author) {
    final String id = "author-" + authors.size();
    author.setId(id);
    authors.add(author);
    return author;
  }

  @Override
  public Author updateAuthor(Author author) {
    final Optional<Author> foundAuthor = authors.stream().filter(a -> a.getId().equals(author.getId())).findFirst();
    if (foundAuthor.isPresent()) {
      Author existingAuthor = foundAuthor.get();
      Optional.ofNullable(author.getFirstName()).ifPresent(existingAuthor::setFirstName);
      Optional.ofNullable(author.getLastName()).ifPresent(existingAuthor::setLastName);
      return existingAuthor;
    } else{
      throw new RuntimeException("Could not find author with id " + author.getId());
    }
  }

  @Override
  public Book updateBook(Book book) {
    final Optional<Book> foundBook = books.stream().filter(b -> b.getId().equals(book.getId())).findFirst();
    if (foundBook.isPresent()) {
      Book existingBook = foundBook.get();
      Optional.ofNullable(book.getName()).ifPresent(existingBook::setName);
      Optional.ofNullable(book.getPageCount()).ifPresent(existingBook::setPageCount);
      Optional.ofNullable(book.getAuthorId()).ifPresent(existingBook::setAuthorId);
      return existingBook;
    } else{
      throw new RuntimeException("Could not find book with id " + book.getId());
    }
  }

  @PostConstruct
  public void init() {
    books.addAll(Arrays.asList(
        Book.builder()
            .id("book-0")
            .name("Harry Potter and the Philosopher's Stone")
            .pageCount(223)
            .authorId("author-0")
            .build(),
        Book.builder()
            .id("book-1")
            .name("Moby Dick")
            .pageCount(635)
            .authorId("author-1")
            .build(),
        Book.builder()
            .id("book-2")
            .name("Interview with the vampire")
            .pageCount(371)
            .authorId("author-2")
            .build()
    ));

    authors.addAll(Arrays.asList(
        Author.builder()
            .id("author-0")
            .firstName("Joanne")
            .lastName("Rownling")
            .build(),
        Author.builder()
            .id("author-1")
            .firstName("Herman")
            .lastName("Melville")
            .build(),
        Author.builder()
            .id("author-2")
            .firstName("Anne")
            .lastName("Ricce")
            .build()
    ));

  }
}
