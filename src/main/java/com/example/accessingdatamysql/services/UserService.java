package com.example.accessingdatamysql.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.accessingdatamysql.models.User;
import com.example.accessingdatamysql.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUser(int id) {
    return userRepository.findById(id);
  }

  @Transactional
  public void deleteUser(int id) {
    userRepository.deleteById(id);
  }

  @Transactional
  public User add(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public Optional<User> update(User user, int id) {
    return userRepository.findById(id)
        .map(newUser -> {
          newUser.setName(user.getName());
          newUser.setEmail(user.getEmail());
          return userRepository.save(newUser);
        });
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public boolean existsUserById(int id) {
    return userRepository.existsById(id);
  }

  public boolean existsUsers() {
    return userRepository.count() > 0 ? true : false;
  }
}
