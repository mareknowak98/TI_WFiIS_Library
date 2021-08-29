package zti.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import zti.library.exception.AuthorNotFoundException;
import zti.library.model.Author;
import zti.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author addAuthor(Author book){
        return authorRepository.save(book);
    }

    public List<Author> getAuthors(){
        return StreamSupport.stream(authorRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Author getAuthor(Long id){
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public Author deleteAuthor(Long id) {
        Author author = getAuthor(id);
        authorRepository.delete(author);
        return author;
    }

    @Transactional
    public Author editAuthor(Long id, Author author){
        Author authorToEdit = getAuthor(id);
        authorToEdit.setAuthor(author.getAuthor());
        return authorToEdit;
    }

}
