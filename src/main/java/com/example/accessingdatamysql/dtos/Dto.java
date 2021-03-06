package com.example.accessingdatamysql.dtos;

import javax.validation.constraints.NotBlank;

public class Dto {

  @NotBlank
  private String name;

  @NotBlank
  private String email;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
