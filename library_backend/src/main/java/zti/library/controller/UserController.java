package zti.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zti.library.dto.*;
import zti.library.model.Book;
import zti.library.model.Borrowed;
import zti.library.model.Category;
import zti.library.model.User;
import zti.library.repository.BorrowedRepository;
import zti.library.security.CurrentUser;
import zti.library.security.UserPrincipal;
import zti.library.service.BookService;
import zti.library.service.BorrowedService;
import zti.library.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private BookService bookService;
    private BorrowedService borrowedService;

    @Autowired
    public UserController(UserService userService, BookService bookService, BorrowedService borrowedService) {
        this.borrowedService = borrowedService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("all") //TODO delete it
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<User> users = userService.getUsers();
        List<UserDto> usersDto = users.stream().map(UserDto::from).collect(Collectors.toList()); //:: is 'range operator'
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @GetMapping("me")
//    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return userService.getCurrentUser(currentUser);
    }

    @PostMapping("addBorrowed")
    public ResponseEntity<BorrowedDto> addBorrowed(@CurrentUser UserPrincipal currentUser, @RequestBody final BorrowedDto borrowedDto){
        Borrowed borrowed = borrowedService.addBorrowed(Borrowed.from(borrowedDto));
        Long currentUserId = userService.getCurrentUser(currentUser).getId();
        User user = userService.getUser(currentUserId);

        userService.addBorrowedToUser(currentUserId, borrowed.getId());

        return new ResponseEntity<>(BorrowedDto.from(borrowed), HttpStatus.OK);
    }

    @PostMapping("addBorrowedToUser/{userId}/{bookId}/add")
    public ResponseEntity<BorrowedDto> addBorrowedToUser(@PathVariable final Long userId, @PathVariable final Long bookId, @RequestBody final BorrowedDto borrowedDto){
        Borrowed borrowed = borrowedService.addBorrowed(Borrowed.from(borrowedDto));
        userService.addBorrowedToUser(userId, borrowed.getId());
        bookService.addBorrowedToBook(bookId, borrowed.getId());
        return new ResponseEntity<>(BorrowedDto.from(borrowed), HttpStatus.OK);
    }

    @PutMapping("addBorrowedToUser/return")
    public ResponseEntity<BorrowedDto> returnBorrowedToUser(@RequestBody final BorrowedDto borrowedDto){
        Borrowed borrowedToEdit = borrowedService.editBorrowed(borrowedDto.getId(), Borrowed.from(borrowedDto));
        return new ResponseEntity<>(BorrowedDto.from(borrowedToEdit), HttpStatus.OK);
    }


}
