package zti.library.model;

import lombok.Data;
import zti.library.dto.BorrowedDto;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "Borrowed")
public class Borrowed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="book_id")
    private Book book; //Item

    @ManyToOne
    private User user;

    private Date startDate;

    private Date dueDate;

    private boolean returned;

    public boolean getReturned(){return this.returned;}

    public static Borrowed from(BorrowedDto borrowedDto){
        Borrowed borrowed = new Borrowed();
//        borrowed.setBook(borrowedDto.getBook());
        borrowed.setStartDate(borrowedDto.getStartDate());
        borrowed.setDueDate(borrowedDto.getDueDate());
        borrowed.setReturned(borrowedDto.getReturned());
        return borrowed;
    }
}