package zti.library;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.util.Collections;

import zti.library.dto.SignUpRequest;
import zti.library.exception.AppException;
import zti.library.service.AuthService;

import zti.library.repository.*;
import zti.library.model.*;

@Component
@Slf4j
public class DatabaseLoader implements ApplicationRunner {
//    private final BookRepository books;

    private final UserRepository users;
    private final RoleRepository roles;
    private PasswordEncoder passwordEncoder;
    private final AuthorRepository authors;
    private final BookRepository books;

    @Autowired
    public DatabaseLoader(UserRepository users, RoleRepository roles, PasswordEncoder passwordEncoder, AuthorRepository authors, BookRepository books) {
        this.users = users;
        this.roles = roles;
        this.passwordEncoder= passwordEncoder;
        this.authors = authors;
        this.books = books;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Admin mock
        User user = new User("admin", "admin@gmail.com", "password");
        user.setPassword(passwordEncoder.encode("password"));
        Role userRole = roles.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User Role not set. Add default roles to database."));

        user.setRoles(Collections.singleton(userRole));
        log.info("Successfully registered admin with [email: {}]", user.getEmail());
        users.save(user);

        //Authors mocks
        Author rowling = this.authors.save(new Author("J. K. Rowling"));
        Author tolkien = this.authors.save(new Author("J.R.R. Tolkien"));
        Author sparks = this.authors.save(new Author("Nicholas Sparks"));
        Author collins = this.authors.save(new Author("Suzanne Collins"));
        Author king = this.authors.save(new Author("Stephen King"));
        log.info("Successfully registered author with [author: {}]", rowling.getAuthor());
        log.info("Successfully registered author with [author: {}]", tolkien.getAuthor());
        log.info("Successfully registered author with [author: {}]", sparks.getAuthor());
        log.info("Successfully registered author with [author: {}]", collins.getAuthor());
        log.info("Successfully registered author with [author: {}]", king.getAuthor());

//        //Books mocks
//        Book hp1 = new Book("Harry Potter and the Philosophers Stone");
//        this.books.save(hp1);
//        hp1.addAuthorToBook(rowling);
////        rowling.addAuthorToBook(hp1);
//
//        Book hobbit = new Book("The Hobbit");
//        this.books.save(hobbit);
//        hobbit.addAuthor(tolkien);
//
////        tolkien.addAuthorToBook(hobbit);

        //

    }
}
