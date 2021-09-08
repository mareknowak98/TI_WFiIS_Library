package zti.library.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zti.library.dto.BorrowedDto;
import zti.library.dto.CategoryDto;
import zti.library.model.Book;
import zti.library.model.Borrowed;
import zti.library.model.Category;
import zti.library.model.User;
import zti.library.repository.UserRepository;
import zti.library.service.BorrowedService;
import zti.library.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/borrow")
public class BorrowedController {
    private final BorrowedService borrowedService;
    private final UserService userService;

    @Autowired
    public BorrowedController(BorrowedService borrowedService, UserService userService){
        this.borrowedService = borrowedService;
        this.userService = userService;
    }

    @GetMapping(value = "getUserBorrowed/{userId}")
    public ResponseEntity<List<BorrowedDto>> getBorrowedByUser(@PathVariable final Long userId){
        User user = userService.getUser(userId);
        List<Borrowed> borrowed = borrowedService.getBorrowedByUser(user);
        return new ResponseEntity<>(borrowed.stream().map(BorrowedDto::from).collect(Collectors.toList()), HttpStatus.OK);
    }


}