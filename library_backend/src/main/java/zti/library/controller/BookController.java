package zti.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zti.library.dto.AuthorDto;
import zti.library.dto.BookDto;
import zti.library.dto.CategoryDto;
import zti.library.model.Author;
import zti.library.model.Book;
import zti.library.service.BookService;
import zti.library.service.AuthorService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    @Autowired
    public BookController(BookService bookService, AuthorService authorService){
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> addBook(@RequestBody final BookDto bookDto){
        Book book = bookService.addBook(Book.from(bookDto));
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks(){
        List<Book> books = bookService.getBooks();
        List<BookDto> booksDto = books.stream().map(BookDto::from).collect(Collectors.toList()); //:: is 'range operator'
        return new ResponseEntity<>(booksDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable final Long id){
        Book book = bookService.getBook(id);
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable final Long id){
        log.info("Deleting book with id [id: {}]", id);
        Book book = bookService.deleteBook(id);
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<BookDto> editBook(@PathVariable final Long id, @RequestBody final BookDto bookDto){
        Book editedBook = bookService.editBook(id, Book.from(bookDto));
        return new ResponseEntity<>(BookDto.from(editedBook), HttpStatus.OK);
    }

    @PostMapping(value = "{bookId}/authors/{authorId}/add")
    public ResponseEntity<BookDto> addAuthorToBook(@PathVariable final Long bookId, @PathVariable final Long authorId){
        Book book = bookService.addAuthorToBook(bookId, authorId);
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @PostMapping(value = "{bookId}/addManyAuthors")
    public ResponseEntity<BookDto> addAuthorsToBook(@PathVariable final Long bookId, @RequestBody final List<AuthorDto> authorDtos){
        Book book = new Book();
        for (AuthorDto authorDto : authorDtos) {
            book = bookService.addAuthorToBook(bookId, authorDto.getId());
        }
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @DeleteMapping(value = "{bookId}/authors/{authorId}/remove")
    public ResponseEntity<BookDto> removeAuthorFromBook(@PathVariable final Long bookId, @PathVariable final Long authorId){
        Book book = bookService.removeAuthorFromBook(bookId, authorId);
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @PostMapping(value = "{bookId}/categories/{categoryId}/add")
    public ResponseEntity<BookDto> addCategoryToBook(@PathVariable final Long bookId, @PathVariable final Long categoryId){
        Book book = bookService.addCategoryToBook(bookId, categoryId);
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @PostMapping(value = "{bookId}/addManyCategories")
    public ResponseEntity<BookDto> addCategoriesToBook(@PathVariable final Long bookId, @RequestBody final List<CategoryDto> categoryDtos){
        Book book = new Book();
        for (CategoryDto categoryDto : categoryDtos) {
            book = bookService.addCategoryToBook(bookId, categoryDto.getId());
        }
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @DeleteMapping(value = "{bookId}/categories/{categoryId}/remove")
    public ResponseEntity<BookDto> removeCategoryFromBook(@PathVariable final Long bookId, @PathVariable final Long categoryId){
        Book book = bookService.removeCategoryFromBook(bookId, categoryId);
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

}
