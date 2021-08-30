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
    public ResponseEntity<ReservationDto> addBorrowed(@CurrentUser UserPrincipal currentUser, @PathVariable final Long bookId, @RequestBody final ReservationDto reservationDto){
        Reservation reservation = reservationService.addReservation(Reservation.from(reservationDto));
        Long currentUserId = userService.getCurrentUser(currentUser).getId();
        User user = userService.getUser(currentUserId);

        userService.addReservationToUser(currentUserId, reservation.getId());
        bookService.addReservationToBook(bookId, reservation.getId());

        return new ResponseEntity<>(ReservationDto.from(reservation), HttpStatus.OK);
    }

}
