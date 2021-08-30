package zti.library.model;

import lombok.Data;
import zti.library.dto.AuthorDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_written",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> booksWritten = new ArrayList<>();

    public Author(){ }

    public static Author from(AuthorDto authorDto){
        Author author = new Author();
        author.setAuthor(authorDto.getAuthor());
        return author;
    }

    public Author(String author){
        this.author = author;
    }

    public void addAuthorToBook(Book book){ booksWritten.add(book); }
    public void removeAuthorFromBook(Book book){ booksWritten.remove(book); }

}
