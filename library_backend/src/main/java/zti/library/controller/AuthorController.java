package zti.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zti.library.dto.AuthorDto;
import zti.library.dto.BookDto;
import zti.library.model.Author;
import zti.library.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody final AuthorDto authorDto){
        Author author = authorService.addAuthor(Author.from(authorDto));
        return new ResponseEntity<>(AuthorDto.from(author), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAuthors(){
        List<Author> authors = authorService.getAuthors();
        List<AuthorDto> authorsDto = authors.stream().map(AuthorDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(authorsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable final Long id){
        Author author = authorService.getAuthor(id);
        return new ResponseEntity<>(AuthorDto.from(author), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable final Long id){
        Author author = authorService.deleteAuthor(id);
        return new ResponseEntity<>(AuthorDto.from(author), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<AuthorDto> editAuthor(@PathVariable final Long id, @RequestBody final AuthorDto authorDto){
        Author authorToEdit = authorService.editAuthor(id, Author.from(authorDto));
        return new ResponseEntity<>(AuthorDto.from(authorToEdit), HttpStatus.OK);
    }


}
