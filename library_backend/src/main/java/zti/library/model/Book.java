package zti.library.model;

import lombok.Data;
import zti.library.dto.BookDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private List<Author> authors = new ArrayList<>();

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

}
