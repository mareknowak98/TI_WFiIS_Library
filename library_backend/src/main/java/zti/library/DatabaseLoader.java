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

    @Autowired
    public DatabaseLoader(UserRepository users, RoleRepository roles, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.roles = roles;
        this.passwordEncoder= passwordEncoder;
    }

////    @Override
//    public void run(String... strings) throws Exception {
//
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User user = new User("admin", "admin@gmail.com", "password");
//        roles.save(new Role(new Long(1), RoleName.ROLE_ADMIN));
        user.setPassword(passwordEncoder.encode("password"));


        Role userRole = roles.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User Role not set. Add default roles to database."));

        user.setRoles(Collections.singleton(userRole));

        log.info("Successfully registered admin with [email: {}]", user.getEmail());

        users.save(user);
    }
}
