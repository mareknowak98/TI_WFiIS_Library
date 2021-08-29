package zti.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.library.dto.UserSummary;
import zti.library.exception.AuthorNotFoundException;
import zti.library.model.Author;
import zti.library.repository.AuthorRepository;
import zti.library.repository.UserRepository;
import zti.library.security.UserPrincipal;
import zti.library.model.User;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserSummary getCurrentUser(UserPrincipal userPrincipal) {
        return UserSummary.builder()
                .id(userPrincipal.getId())
                .email(userPrincipal.getEmail())
                .name(userPrincipal.getName())
                .build();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }
}
