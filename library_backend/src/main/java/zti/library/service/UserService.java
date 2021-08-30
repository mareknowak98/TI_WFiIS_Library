package zti.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.library.dto.UserSummary;
import zti.library.exception.BorrowedNotFoundException;
import zti.library.model.Borrowed;
import zti.library.model.User;
import zti.library.repository.BorrowedRepository;
import zti.library.repository.UserRepository;
import zti.library.security.UserPrincipal;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BorrowedService borrowedService;


    @Autowired
    public UserService(UserRepository userRepository, BorrowedService borrowedService){
        this.userRepository = userRepository;
        this.borrowedService = borrowedService;
    }


    public UserSummary getCurrentUser(UserPrincipal userPrincipal) {
        return UserSummary.builder()
                .id(userPrincipal.getId())
                .email(userPrincipal.getEmail())
                .name(userPrincipal.getName())
                .build();
    }

    public List<User> getUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> new BorrowedNotFoundException(id)); //TODO
    }

    @Transactional
    public Borrowed addBorrowedToUser(Long userId, Long borrowedId){
        Borrowed borrowed = borrowedService.getBorrowed(borrowedId);
        User user = getUser(userId);
        user.addBorrowed(borrowed);
        borrowed.setUser(user);
        return borrowed;
    }

    @Transactional
    public Borrowed removeBorrowedToUser(Long userId, Long borrowedId){
        Borrowed borrowed = borrowedService.getBorrowed(borrowedId);
        User user = getUser(userId);
        user.removeBorrowed(borrowed);
        return borrowed;
    }
}
