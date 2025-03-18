📌 Objetivo del Proyecto: Obtención Masiva de Datos con Concurrencia en Java
Este proyecto tiene como objetivo demostrar cómo obtener datos de una API pública de manera eficiente utilizando concurrencia en Java. 
Se busca maximizar el rendimiento del sistema mediante la ejecución de múltiples solicitudes en paralelo, evitando bloqueos y optimizando el tiempo de respuesta.

🚀 Tecnologías Utilizadas
Para cumplir con este objetivo, el proyecto se basa en varias tecnologías clave:

Java 17

Se eligió Java 17 por ser una versión LTS (Long-Term Support) con mejoras en rendimiento y concurrencia.
Compatible con las APIs modernas de HTTP (java.net.http.HttpClient) y con el uso de ExecutorService.
Spring Boot

Simplifica el desarrollo de aplicaciones Java con un enfoque modular y escalable.
Permite crear una API REST de forma rápida mediante Spring MVC.
Facilita la gestión de dependencias y configuración con Spring Boot Starter.
Jigsaw (Modularidad en Java)

Se usa Jigsaw para dividir el código en módulos reutilizables.
Permite una mejor organización y encapsulación del código con module-info.java.
Concurrencia con ExecutorService

Se usa un Thread Pool (FixedThreadPool) para manejar múltiples solicitudes concurrentes a la API.
Esto evita bloqueos en la ejecución y mejora la eficiencia en la obtención de datos.

🛠 Explicación del Funcionamiento
Obtención de datos desde una API Pública

Se elige la API https://jsonplaceholder.typicode.com/users, que devuelve información de usuarios en formato JSON.
Se utiliza HttpClient para realizar la solicitud HTTP y obtener los datos.
Procesamiento Concurrente de Datos

Se usa ExecutorService con un Thread Pool de 10 hilos para procesar múltiples solicitudes en paralelo.
Los datos se convierten a objetos User, y se filtran para extraer los nombres más repetidos.
Exposición de los Resultados a través de una API REST

Se usa Spring Boot para exponer un endpoint /api/names que devuelve los nombres más repetidos en formato JSON.


📈 Beneficios del Enfoque Utilizado
✅ Rendimiento Mejorado: Al ejecutar múltiples solicitudes en paralelo, se reduce el tiempo de respuesta.
✅ Escalabilidad: El uso de ExecutorService permite manejar grandes volúmenes de datos sin afectar el rendimiento.
✅ Código Modular y Reutilizable: Gracias a Jigsaw, el código está bien estructurado y puede integrarse en otros proyectos.
✅ Fácil Integración con Otras APIs: La arquitectura permite extender el proyecto para consumir diferentes fuentes de datos.

🎯 Casos de Uso
Este enfoque puede aplicarse en escenarios como:

Análisis de datos financieros: Obtener cotizaciones de múltiples fuentes simultáneamente.
Monitoreo de redes sociales: Analizar hashtags más populares desde distintas plataformas.
Gestión de imágenes: Descargar imágenes concurrentemente y calcular su tamaño total.
