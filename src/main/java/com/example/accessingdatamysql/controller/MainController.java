package com.example.accessingdatamysql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class MainController {

  @GetMapping(path="/")
  public @ResponseBody String demo(){
    return "Hi";
  }
}
