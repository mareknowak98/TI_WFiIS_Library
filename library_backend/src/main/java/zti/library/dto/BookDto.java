package zti.library.dto;

import zti.library.model.Author;
import zti.library.model.Book;
import lombok.Data;
import zti.library.model.Category;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookDto {
    private Long id;
    private String name;
    private List<Author> authors = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    public static BookDto from(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthors(book.getAuthors());
        bookDto.setName(book.getName());
        bookDto.setCategories(book.getCategories());
        return bookDto;
    }

}
