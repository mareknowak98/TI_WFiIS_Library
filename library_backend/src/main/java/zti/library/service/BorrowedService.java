package zti.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import zti.library.exception.BorrowedNotFoundException;
import zti.library.model.Borrowed;
import org.springframework.stereotype.Service;
import zti.library.repository.BorrowedRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

@Service
public class BorrowedService {
    private final BorrowedRepository borrowedRepository;

    @Autowired
    public BorrowedService(BorrowedRepository borrowedRepository){
        this.borrowedRepository = borrowedRepository;
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

}