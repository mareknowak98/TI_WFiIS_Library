package zti.library.dto;

import zti.library.model.Author;
import zti.library.model.Book;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookDto {
    private Long id;
    private String name;
    private List<Author> authors = new ArrayList<>();

    public static BookDto from(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthors(book.getAuthors());
        bookDto.setName(book.getName());
        return bookDto;
    }

}
