package com.example.accessingdatamysql.services;

import java.util.Optional;

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

	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	public void add(User user) {
		userRepository.save(user);
	}

	public void update(User user, int id) {
		userRepository.findById(id)
		    .map(newUser -> {
		      newUser.setName(user.getName());
		      newUser.setEmail(user.getEmail());
		      return userRepository.save(newUser);
		    });
	}
}
