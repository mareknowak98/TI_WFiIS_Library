package zti.library.model;

import lombok.Data;
import zti.library.dto.AuthorDto;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;

    public Author(){

    }

    public static Author from(AuthorDto authorDto){
        Author author = new Author();
        author.setAuthor(authorDto.getAuthor());
        return author;
    }

    public Author(String author){
        this.author = author;
    }
}
