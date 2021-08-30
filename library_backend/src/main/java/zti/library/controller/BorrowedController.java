package zti.library.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zti.library.service.BorrowedService;

@RestController
@RequestMapping("/borrow")
public class BorrowedController {
    private final BorrowedService borrowedService;

    @Autowired
    public BorrowedController(BorrowedService borrowedService){
        this.borrowedService = borrowedService;
    }

}