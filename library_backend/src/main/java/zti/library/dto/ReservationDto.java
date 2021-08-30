package zti.library.dto;

import lombok.Data;
import zti.library.model.Reservation;

import java.sql.Date;

@Data
public class ReservationDto {
    private Long id;

    private Date dueDate;

    public static ReservationDto from(Reservation borrowed){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(borrowed.getId());
        reservationDto.setDueDate(borrowed.getDueDate());
        return reservationDto;
    }
}