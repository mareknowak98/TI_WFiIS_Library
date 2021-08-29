package zti.library.dto;

import zti.library.model.Author;
import zti.library.model.Book;
import lombok.Data;
import zti.library.model.Borrowed;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookDto {
    private Long id;
    private String name;
    private List<Author> authors = new ArrayList<>();
    private List<BorrowedDto> borrowedDto = new ArrayList<>();

    public static BookDto from(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthors(book.getAuthors());
        bookDto.setBorrowedDto(book.getBorrowed().stream().map(BorrowedDto::from).collect(Collectors.toList()));
        bookDto.setName(book.getName());
        return bookDto;
    }

}
