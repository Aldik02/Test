package com.test_task.service;

import com.test_task.model.User;
import com.test_task.repos.UserRepository;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User get(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public Long create(final User user) {
        return userRepository.save(user).getId();
    }

    public void createUser(final User user) {userRepository.save(new User(user.getFullName(),user.getUsername(),bCryptPasswordEncoder().encode(user.getPasswordHash())));}

    public Boolean userExistsByUsername(String username) {
        List<User> Users = findAll();
        Boolean found = Boolean.FALSE;
        for(User u: Users){
            if(u.getUsername().equals(username)){
                found = Boolean.TRUE;
            }
        }
        return found;
    }

    public void update(final Long id, final User user) {
        final User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        userRepository.save(user);
    }

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }
}
