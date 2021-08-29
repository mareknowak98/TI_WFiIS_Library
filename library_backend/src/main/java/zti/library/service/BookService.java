package zti.library.service;

import zti.library.exception.BookNotFoundException;
import zti.library.model.Author;
import zti.library.model.Book;
import zti.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getBooks(){
        return StreamSupport.stream(bookRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Book getBook(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book deleteBook(Long id) {
        Book book = getBook(id);
        bookRepository.delete(book);
        return book;
    }

    @Transactional
    public Book editBook(Long id, Book book){
        Book bookToEdit = getBook(id);
        bookToEdit.setName(book.getName());
        return bookToEdit;
    }

    @Transactional
    public Book addAuthorToBook(Long bookId, Long authorId){
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        book.addAuthor(author);
        return book;
    }

    @Transactional
    public Book removeAuthorFromBook(Long bookId, Long authorId){
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        book.removeAuthor(author);
        return book;
    }
}
