package com.example.accessingdatamysql.controller;
import java.util.Optional;

import com.example.accessingdatamysql.dtos.Dto;
import com.example.accessingdatamysql.models.User;
import com.example.accessingdatamysql.repositories.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

  @Autowired
  private UserRepository userRepository;

  // curl -H "Content-Type: application/json" -X POST -d '{"name":"myName","email":"my@email"}' http://localhost:8080/demo/save
  // CREATE
  @PostMapping(path = "/save")
  public @ResponseBody String save(@RequestBody Dto dto) {
    
    User user = new User();
    BeanUtils.copyProperties(dto, user);
    userRepository.save(user);
    return "Saved\n";
  }

  // http://localhost:8080/demo/7
  // GET
  @GetMapping(path = "/{id}")
  public @ResponseBody Optional<User> get(@PathVariable int id) {
    return userRepository.findById(id);
  }
 
  // curl -X PUT http://localhost:8080/demo/7 -H 'Content-type:application/json' -d '{"name":"Samwise Gamgee", "email":"ring@bearer"}'
  // UPDATE
  @PutMapping(path = "/{id}")
  public @ResponseBody String replace(@RequestBody Dto dto, @PathVariable int id) {
    
    User user = new User();
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    
    userRepository.findById(id)
    .map(u -> {
      u.setName(user.getName());
      u.setEmail(user.getEmail());
      return userRepository.save(u);
    });
    return "updated\n";
  }
  
  //curl -X DELETE http://localhost:8080/demo/3
  //DELETE 
  @DeleteMapping(path = "/{id}")
  public @ResponseBody String remove(@PathVariable int id) {
    userRepository.deleteById(id);
    return "deleted\n";
  }

  //http://localhost:8080/demo/update?id=5&name=joe&email=joe@kim
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
  
  // http://localhost:8080/demo/add?name=Jucelino&email=jucelino@email.com
  // CREATE
  @GetMapping(path = "/add")
  public @ResponseBody String add(@RequestParam String name, @RequestParam String email) {
    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Add";
  }
  
  // http://localhost:8080/demo/read?id=7
  //READ
  @GetMapping(path = "/read")
  public @ResponseBody String read(@RequestParam int id) {
    userRepository.findById(id);
    return "Read";
  }

  // http://localhost:8080/demo/delete?id=7
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

  // http://localhost:8080/demo/all
  @GetMapping(path = "/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }
}
