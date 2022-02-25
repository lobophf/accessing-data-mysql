package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.dtos.Dto;
import com.example.accessingdatamysql.models.User;
import com.example.accessingdatamysql.repositories.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

  @Autowired
  private UserRepository userRepository;

  //CREATE
  // http://localhost:8080/demo/add?name=Jucelino&email=jucelino@email.com
  @GetMapping(path = "/add")
  public @ResponseBody String add(@RequestParam String name, @RequestParam String email) {
    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Add";
  }
  

  // curl -H "Content-Type: application/json" -X POST -d '{"name":"myName","email":"my@email"}' http://localhost:8080/demo/save
  @PostMapping(path = "/save")
  public @ResponseBody String save(@RequestBody Dto dto) {
    
    User user = new User();
    BeanUtils.copyProperties(dto, user);
    userRepository.save(user);
    return "Saved\n";
  }

  //UPDATE
  @GetMapping(path = "/update")
  public @ResponseBody String update(@RequestParam int id, @RequestParam String name, @RequestParam String email) {
    
    User newUser = new User();
    newUser.setName(name);
    newUser.setEmail(email);

    userRepository.findById(id)
    .map(u -> {
      u.setName(newUser.getName());
      u.setEmail(newUser.getEmail());
      return userRepository.save(u);
    });
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
