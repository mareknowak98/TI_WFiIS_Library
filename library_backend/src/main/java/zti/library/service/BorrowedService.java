package zti.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import zti.library.exception.BorrowedNotFoundException;
import zti.library.model.Book;
import zti.library.model.Borrowed;
import org.springframework.stereotype.Service;
import zti.library.model.User;
import zti.library.repository.BorrowedRepository;
import zti.library.service.BookService;
import zti.library.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

@Service
public class BorrowedService {
    private final BorrowedRepository borrowedRepository;
    private final BookService bookService;
    private final UserService userService;


    @Autowired
    public BorrowedService(BorrowedRepository borrowedRepository, BookService bookService, UserService userService){
        this.borrowedRepository = borrowedRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    public Borrowed addBorrowed(Borrowed borrowed){
        return borrowedRepository.save(borrowed);
    }

    public List<Borrowed> getBorroweds(){
        return StreamSupport.stream(borrowedRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Borrowed getBorrowed(Long id){
        return borrowedRepository.findById(id).orElseThrow(() -> new BorrowedNotFoundException(id));
    }

    public Borrowed deleteBorrowed(Long id) {
        Borrowed borrowed = getBorrowed(id);
        borrowedRepository.delete(borrowed);
        return borrowed;
    }

    @Transactional
    public Borrowed editBorrowed(Long id, Borrowed borrowed){
        Borrowed borrowedToEdit = getBorrowed(id);
        borrowedToEdit.setBook(borrowed.getBook());
        borrowedToEdit.setUser(borrowed.getUser());
        borrowedToEdit.setDueDate(borrowed.getDueDate());
        borrowedToEdit.setStartDate(borrowed.getStartDate());
        borrowedToEdit.setReturned(borrowed.getReturned());
        return borrowedToEdit;
    }

    public Borrowed borrowBook(Long borrowedId, Long bookId, Long userId){
        Borrowed borrowed = getBorrowed(borrowedId);
        Book book = bookService.getBook(bookId);
        User user = userService.getUser(userId);
        book.addBorrowed(borrowed);
        user.addBorrowed(borrowed);
        borrowed.setBook(book);
        borrowed.setUser(user);
        return borrowed;
    }

}
