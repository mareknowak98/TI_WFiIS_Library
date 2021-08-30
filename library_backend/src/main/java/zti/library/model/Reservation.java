package zti.library.model;

import lombok.Data;
import zti.library.dto.BorrowedDto;
import zti.library.dto.ReservationDto;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="book_reservation_id")
    private Book book; //Item

    @ManyToOne
    private User user;

    private Date dueDate;

    public static Reservation from(ReservationDto reservationDto){
        Reservation reservation = new Reservation();
        reservation.setDueDate(reservationDto.getDueDate());
        return reservation;
    }
}
