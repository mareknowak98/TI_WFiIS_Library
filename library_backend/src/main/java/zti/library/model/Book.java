package zti.library.model;

import lombok.Data;
import zti.library.dto.BookDto;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serialNumber;
    private String name;

    public static Book from(BookDto bookDto){
        Book book = new Book();
        book.setSerialNumber(bookDto.getSerialNumber());
        book.setName(bookDto.getName());
        return book;
    }

}
