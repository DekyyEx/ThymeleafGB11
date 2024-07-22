package org.example.springmvcthymeleaf.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.example.springmvcthymeleaf.model.User;
import org.example.springmvcthymeleaf.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public User getOne(int id) {
        return userRepository.getOne(id);
    }
}
