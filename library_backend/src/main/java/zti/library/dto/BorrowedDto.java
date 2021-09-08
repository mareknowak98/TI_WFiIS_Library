package zti.library.dto;

import zti.library.model.Author;
import zti.library.model.Book;
import zti.library.model.Borrowed;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class BorrowedDto {
    private Long id;
    private BookDto book;
    private Date startDate;

    private Date dueDate;

    private boolean returned;

    public boolean getReturned(){return this.returned;}

    public static BorrowedDto from(Borrowed borrowed){
        BorrowedDto borrowedDto = new BorrowedDto();
        borrowedDto.setBook(BookDto.from(borrowed.getBook()));
        borrowedDto.setId(borrowed.getId());
        borrowedDto.setStartDate(borrowed.getStartDate());
        borrowedDto.setDueDate(borrowed.getDueDate());
        borrowedDto.setReturned(borrowed.getReturned());
        return borrowedDto;
    }
}