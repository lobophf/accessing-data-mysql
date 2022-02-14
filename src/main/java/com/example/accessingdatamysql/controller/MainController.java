package com.example.accessingdatamysql.controller;

import java.util.Optional;

import javax.persistence.Id;

import com.example.accessingdatamysql.models.User;
import com.example.accessingdatamysql.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

  @Autowired
  private UserRepository userRepository;

  //CREATE
  @GetMapping(path = "/add")
  public @ResponseBody String add(@RequestParam String name, @RequestParam String email) {
    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Add";
  }

  //UPDATE
  @GetMapping(path = "/update")
  public @ResponseBody String update(@RequestParam String id, @RequestParam String name, @RequestParam String email) {

    userRepository.deleteById(Integer.valueOf(id));

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);

    return "update";
  }

  //READ
  @GetMapping(path = "/read")
  public @ResponseBody String read(@RequestParam int id) {
    userRepository.findById(id);
    return "Read";
  }

  //DELETE
  @GetMapping(path = "/delete")
  public @ResponseBody String delete(@RequestParam int id) {
    userRepository.deleteById(id);
    return "Delete";
  }

  @GetMapping(path = "/")
  public @ResponseBody String demo() {
    return "Hi";
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }
}
