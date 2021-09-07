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
import zti.library.service.BookService;

@Component
@Slf4j
public class DatabaseLoader implements ApplicationRunner {
//    private final BookRepository books;

    private final UserRepository users;
    private final RoleRepository roles;
    private PasswordEncoder passwordEncoder;
    private final AuthorRepository authors;
    private final CategoryRepository categories;
    private final BookRepository books;
    private final BookService bookService;

    @Autowired
    public DatabaseLoader(UserRepository users, RoleRepository roles, PasswordEncoder passwordEncoder, AuthorRepository authors, CategoryRepository categories, BookRepository books, BookService bookService) {
        this.users = users;
        this.roles = roles;
        this.passwordEncoder= passwordEncoder;
        this.authors = authors;
        this.categories = categories;
        this.books = books;
        this.bookService = bookService;
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

        //User mock
        User user2 = new User("user", "user@gmail.com", "password");
        user2.setPassword(passwordEncoder.encode("password"));
        Role userRole2 = roles.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set. Add default roles to database."));

        user2.setRoles(Collections.singleton(userRole2));
        log.info("Successfully registered admin with [email: {}]", user2.getEmail());
        users.save(user2);

        User user3 = new User("user2", "user2@gmail.com", "password");
        user3.setPassword(passwordEncoder.encode("password"));
        user3.setRoles(Collections.singleton(userRole2));
        log.info("Successfully registered admin with [email: {}]", user3.getEmail());
        users.save(user3);

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

        //Category mocks
        Category fantasy = this.categories.save(new Category("Fantasy"));
        Category adventure = this.categories.save(new Category("Adventure"));
        Category romance = this.categories.save(new Category("Romance"));
        Category thriller = this.categories.save(new Category("Thriller"));
        Category science = this.categories.save(new Category("Science"));
        log.info("Successfully registered category with [category: {}]", fantasy.getCategory());
        log.info("Successfully registered category with [category: {}]", adventure.getCategory());
        log.info("Successfully registered category with [category: {}]", romance.getCategory());
        log.info("Successfully registered category with [category: {}]", thriller.getCategory());
        log.info("Successfully registered category with [category: {}]", science.getCategory());

        //Books mocks
        Book hp1 = new Book("Harry Potter and the Philosophers Stone", "Bloomsbury Pub Ltd", "978-0747532743", 224 , "Harry Potter is an ordinary boy who lives in a cupboard under the stairs at his Aunt Petunia and Uncle Vernon's house, which he thinks is normal...");
        this.books.save(hp1);
        bookService.addAuthorToBook(hp1.getId(), rowling.getId());
        bookService.addCategoryToBook(hp1.getId(), fantasy.getId());
        log.info("Successfully registered book with [name: {}]", hp1.getName());

        Book hp2 = new Book("Harry Potter and the Chamber of Secrets", "Scholastic Paperbacks", "978-0439064873", 341 , "The Dursleys were so mean that hideous that summer that all Harry Potter wanted was to get back to the Hogwarts School for Witchcraft and...");
        this.books.save(hp2);

        bookService.addAuthorToBook(hp2.getId(), rowling.getId());
        bookService.addCategoryToBook(hp2.getId(), fantasy.getId());
        bookService.addCategoryToBook(hp2.getId(), adventure.getId());
        log.info("Successfully registered book with [name: {}]", hp2.getName());

        Book hobbit = new Book("The Hobbit", "Houghton Mifflin Harcourt", "978-0544174221", 384 , "Like every other hobbit, Bilbo Baggins likes nothing better than a quiet evening in his snug hole in the ground, dining on a sumptuous dinner in front of a fire...");
        this.books.save(hobbit);
        bookService.addAuthorToBook(hobbit.getId(), tolkien.getId());
        bookService.addCategoryToBook(hobbit.getId(), fantasy.getId());
        bookService.addCategoryToBook(hobbit.getId(), adventure.getId());

        log.info("Successfully registered book with [name: {}]", hobbit.getName());


        log.info("-------SUCCESSFULLY ADDED ITEMS FROM DATABASE LOADER--------");
    }
}
