package zti.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.library.exception.ReservationNotFoundException;
import zti.library.model.Borrowed;
import zti.library.model.Reservation;
import zti.library.model.User;
import zti.library.repository.ReservationRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservations(){
        return StreamSupport.stream(reservationRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Reservation getReservation(Long id){
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }

    public List<Reservation> getReservationByUser(User user){
//        return borrowedRepository.findByUser(user).orElseThrow(() -> new BorrowedNotFoundException(userId));
        return StreamSupport.stream(reservationRepository.findByUser(user).spliterator(),false).collect(Collectors.toList());
    }

    public Reservation deleteReservation(Long id) {
        Reservation reservation = getReservation(id);
        reservationRepository.delete(reservation);
        return reservation;
    }

    @Transactional
    public Reservation editReservation(Long id, Borrowed borrowed){
        Reservation reservationToEdit = getReservation(id);
//        reservationToEdit.setBook(borrowed.getBook());
//        reservationToEdit.setUser(borrowed.getUser());
        reservationToEdit.setDueDate(borrowed.getDueDate());
        return reservationToEdit;
    }

}