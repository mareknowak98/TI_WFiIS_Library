package zti.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import zti.library.dto.BookDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "booksWritten", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy="book",	cascade= {CascadeType.REMOVE}, orphanRemoval = true) //Cart
    private List<Borrowed> borrowed;

    public Book(String name){
        this.name = name;
    }

    public Book(){}

    public static Book from(BookDto bookDto){
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthors(bookDto.getAuthors());
        return book;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public void removeAuthor(Author author){
        this.authors.remove(author);
    }

    public void addBorrowed(Borrowed borrowed){
        this.borrowed.add(borrowed);
    }

}
