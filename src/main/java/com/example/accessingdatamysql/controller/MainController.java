package com.example.accessingdatamysql.controller;

import javax.validation.Valid;

import com.example.accessingdatamysql.dtos.Dto;
import com.example.accessingdatamysql.models.User;
import com.example.accessingdatamysql.services.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

  @Autowired
  private UserService userService;

  @PostMapping
  public @ResponseBody ResponseEntity<Object> save(@RequestBody @Valid Dto dto) {
    User user = new User();
    BeanUtils.copyProperties(dto, user);

    if(userService.existsByEmail(user.getEmail())){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This email already exists.");
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(userService.add(user));
  }

  @GetMapping
  public @ResponseBody ResponseEntity<Object> getAllUsers() {

    if(!userService.existsUsers()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found.");
    }
    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
  }

  @GetMapping(path = "/{id}")
  public @ResponseBody ResponseEntity<Object> get(@PathVariable(value = "id") int id) {
    if(!userService.existsUserById(id)){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id doesn't exist.");
    }

    return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
  }

  @PutMapping(path = "/{id}")
  public @ResponseBody ResponseEntity<Object> replace(@RequestBody @Valid Dto dto, @PathVariable(value = "id") int id) {

    if(!userService.existsByEmail(dto.getEmail())){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This email doesn't exist.");
    }

    if(!userService.existsUserById(id)){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id doesn't exist.");
    }

    User user = new User();
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());

    return ResponseEntity.status(HttpStatus.OK).body(userService.update(user, id));
  }

  @DeleteMapping(path = "/{id}")
  public @ResponseBody ResponseEntity<String> remove(@PathVariable(value = "id") int id) {

    if(!userService.existsUserById(id)){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id doesn't exist.");
    }

    userService.deleteUser(id);
    return ResponseEntity.status(HttpStatus.OK).body("The user was successfully deleted.");
  }
}