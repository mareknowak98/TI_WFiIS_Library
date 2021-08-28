package zti.library.dto;

import zti.library.model.Book;
import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String serialNumber;
    private String name;

    public static BookDto from(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setSerialNumber(book.getSerialNumber());
        bookDto.setName(book.getName());
        return bookDto;
    }

}
