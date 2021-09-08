package zti.library.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zti.library.dto.BorrowedDto;
import zti.library.dto.ReservationDto;
import zti.library.model.Borrowed;
import zti.library.model.Reservation;
import zti.library.model.User;
import zti.library.security.CurrentUser;
import zti.library.security.UserPrincipal;
import zti.library.service.BookService;
import zti.library.service.BorrowedService;
import zti.library.service.ReservationService;
import zti.library.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reserve")
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public ReservationController(ReservationService reservationService, BookService bookService, UserService userService){
        this.reservationService = reservationService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping("addReservation/{bookId}")
    public ResponseEntity<ReservationDto> addReservation(@CurrentUser UserPrincipal currentUser, @PathVariable final Long bookId, @RequestBody final ReservationDto reservationDto){
        Reservation reservation = reservationService.addReservation(Reservation.from(reservationDto));
        Long currentUserId = userService.getCurrentUser(currentUser).getId();
        User user = userService.getUser(currentUserId);

        userService.addReservationToUser(currentUserId, reservation.getId());
        bookService.addReservationToBook(bookId, reservation.getId());

        return new ResponseEntity<>(ReservationDto.from(reservation), HttpStatus.OK);
    }

    @DeleteMapping("removeReservation/{reservationId}")
    public ResponseEntity<ReservationDto> removeReservation(@CurrentUser UserPrincipal currentUser, @PathVariable final Long reservationId){
        Reservation reservation = reservationService.deleteReservation(reservationId);
        return new ResponseEntity<>(ReservationDto.from(reservation), HttpStatus.OK);
    }

    @GetMapping(value = "getUserBorrowed/{userId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUser(@PathVariable final Long userId){
        User user = userService.getUser(userId);
        List<Reservation> borrowed = reservationService.getReservationByUser(user);
        return new ResponseEntity<>(borrowed.stream().map(ReservationDto::from).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "getUserBorrowed/me")
    public ResponseEntity<List<ReservationDto>> getReservationsByMe(@CurrentUser UserPrincipal currentUser){
        Long currentUserId = userService.getCurrentUser(currentUser).getId();
        User user = userService.getUser(currentUserId);
        List<Reservation> borrowed = reservationService.getReservationByUser(user);
        return new ResponseEntity<>(borrowed.stream().map(ReservationDto::from).collect(Collectors.toList()), HttpStatus.OK);
    }

}
