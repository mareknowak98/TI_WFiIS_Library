package zti.library.exception;

import java.text.MessageFormat;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long id){
        super(MessageFormat.format("Could not find reservation record with id: {0}", id));
    }
}