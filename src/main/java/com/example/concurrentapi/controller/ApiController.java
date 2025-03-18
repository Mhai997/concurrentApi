package com.example.concurrentapi.controller;

import com.example.concurrentapi.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ApiController {
  private final ApiService apiService;

  public ApiController(ApiService apiService) {
    this.apiService = apiService;
  }

  @GetMapping("/names")
  public Map<String, Long> getMostRepeatedNames() throws ExecutionException, InterruptedException {
    return apiService.fetchMostRepeatedNames();
  }
}
