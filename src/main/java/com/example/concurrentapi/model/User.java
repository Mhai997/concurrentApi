package com.example.concurrentapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//Define la clase User, que representa un usuario obtenido de la API.
//Se usa @JsonIgnoreProperties(ignoreUnknown = true) para evitar errores si la API devuelve más propiedades de las que hemos definido
// (por ejemplo, id, email).
@JsonIgnoreProperties(ignoreUnknown = true)

public class User {
  private String name;

  public User() {}

  public User(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
