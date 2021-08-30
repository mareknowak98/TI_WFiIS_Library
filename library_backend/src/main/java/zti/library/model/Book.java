package zti.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToMany(mappedBy = "booksWritten", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "borrowed_book_id")
    private List<Borrowed> borrowed; //Cart

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_book_id")
    private List<Reservation> reservations; //Cart

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

    public void addBorrowed(Borrowed borrowed){
        this.borrowed.add(borrowed);
    }
    public void removeBorrowed(Borrowed borrowed){
        this.borrowed.remove(borrowed);
    }

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }
    public void removeReservation(Reservation reservation){
        this.reservations.remove(reservation);
    }
}
