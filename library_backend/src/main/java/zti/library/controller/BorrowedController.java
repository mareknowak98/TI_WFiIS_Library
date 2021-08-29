package zti.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zti.library.dto.AuthorDto;
import zti.library.dto.BookDto;
import zti.library.dto.BorrowedDto;
import zti.library.model.Author;
import zti.library.model.Borrowed;
import zti.library.service.AuthorService;
import zti.library.service.BorrowedService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/borrow")
public class BorrowedController {
    private final BorrowedService borrowedService;

    @Autowired
    public BorrowedController(BorrowedService borrowedService){
        this.borrowedService = borrowedService;
    }

    @PostMapping
    public ResponseEntity<BorrowedDto> addBorrowed(@RequestBody final BorrowedDto borrowedDto){
        Borrowed borrowed = borrowedService.addBorrowed(Borrowed.from(borrowedDto));
        return new ResponseEntity<>(BorrowedDto.from(borrowed), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BorrowedDto>> getBorroweds(){
        List<Borrowed> borrowed = borrowedService.getBorroweds();
        List<BorrowedDto> borrowedDtos = borrowed.stream().map(BorrowedDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(borrowedDtos, HttpStatus.OK);
    }

}
