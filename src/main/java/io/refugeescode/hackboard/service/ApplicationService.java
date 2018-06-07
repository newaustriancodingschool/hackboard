package io.refugeescode.hackboard.service;

import io.refugeescode.hackboard.domain.User;
import io.refugeescode.hackboard.repository.UserRepository;
import io.refugeescode.hackboard.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getCurrentUser() {

        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            String userlogin = SecurityUtils.getCurrentUserLogin().get();
            Optional<User> oneByLogin = userRepository.findOneByLogin(userlogin);
            if (oneByLogin.isPresent()) {
                return oneByLogin;
            }
        }
        return null;
    }

}
