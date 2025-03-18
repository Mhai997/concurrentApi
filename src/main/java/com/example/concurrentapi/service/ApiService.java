package com.example.concurrentapi.service;

import com.example.concurrentapi.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class ApiService {
  private static final String API_URL = "https://jsonplaceholder.typicode.com/users";
  private static final HttpClient client = HttpClient.newHttpClient();
  private static final ObjectMapper mapper = new ObjectMapper();

  public Map<String, Long> fetchMostRepeatedNames() throws InterruptedException, ExecutionException {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    Callable<List<User>> task = this::fetchData;
    Future<List<User>> future = executor.submit(task);
    List<User> users = future.get();
    executor.shutdown();
    return users.stream()
        .map(User::getName)
        .collect(Collectors.groupingBy(name -> name, Collectors.counting()));
  }

  private List<User> fetchData() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(API_URL))
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return mapper.readValue(response.body(), new TypeReference<List<User>>() {});
  }
}
