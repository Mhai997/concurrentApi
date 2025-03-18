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

//HttpClient → Para hacer la solicitud HTTP a la API.
//ObjectMapper → Convierte el JSON recibido en objetos User.
//ExecutorService → Maneja concurrencia con un pool de hilos.
//Collectors → Procesa los datos para contar los nombres más repetidos.
@Service
public class ApiService {
  private static final String API_URL = "https://jsonplaceholder.typicode.com/users";
  private static final HttpClient client = HttpClient.newHttpClient();
  private static final ObjectMapper mapper = new ObjectMapper();
//Crea un ExecutorService con 10 hilos (FixedThreadPool(10)) → Permite ejecutar tareas en paralelo.
//Define una tarea Callable<List<User>> que obtiene datos de la API llamando a fetchData().
//Envía la tarea al ExecutorService y obtiene un Future<List<User>> → Esto permite ejecutar la tarea en segundo plano sin bloquear el hilo principal.
//Espera el resultado con future.get() → Recupera la lista de usuarios una vez que la tarea ha finalizado.
//Apaga el ExecutorService (executor.shutdown()) → Evita fugas de memoria.
//Procesa los datos usando Collectors.groupingBy():
//Obtiene solo los nombres de los usuarios (map(User::getName)).
//Cuenta cuántas veces aparece cada nombre.
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
//Construye una solicitud HTTP GET a la URL de la API (API_URL).
//Ejecuta la solicitud con client.send(request, HttpResponse.BodyHandlers.ofString()).
//Convierte la respuesta JSON en una List<User> usando ObjectMapper.readValue().
//Devuelve la lista de usuarios obtenidos.
  private List<User> fetchData() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(API_URL))
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return mapper.readValue(response.body(), new TypeReference<List<User>>() {});
  }

  // Resumen del Flujo de Ejecución
  //fetchMostRepeatedNames()
  //
  //Inicia ExecutorService con 10 hilos.
  //Lanza fetchData() como tarea concurrente.
  //Espera el resultado y procesa los nombres más repetidos.
  //Devuelve un Map<String, Long> con los nombres y sus frecuencias.
  //fetchData()
  //
  //Hace una petición HTTP a la API.
  //Convierte el JSON en una lista de objetos User.
  //Devuelve los datos a fetchMostRepeatedNames().
}
