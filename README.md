# Support AI Chat

Este es un proyecto simple de Spring Boot que implementa un chat de atenciÃ³n al cliente utilizando la API de DeepSeek.

## Requisitos

*   Java 17 o superior
*   Maven 3.6 o superior
*   VSCode con el [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

## CÃ³mo Ejecutar en VSCode

1.  **Abrir el Proyecto**: Abre la carpeta `support-ai` directamente en Visual Studio Code.

2.  **Instalar Extensiones**: AsegÃºrate de tener instalado el "Extension Pack for Java" de Microsoft. VSCode deberÃ­a detectarlo automÃ¡ticamente si no lo tienes.

3.  **Ejecutar la AplicaciÃ³n**:
    *   Espera a que VSCode termine de configurar el proyecto Java (puedes ver el progreso en la barra de estado inferior derecha).
    *   Abre el archivo `src/main/java/com/example/supportai/SupportAiApplication.java`.
    *   VerÃ¡s un botÃ³n de `Run` justo encima del mÃ©todo `public static void main(String[] args)`.
    *   Haz clic en `Run`.

4.  **Verificar**: La aplicaciÃ³n se iniciarÃ¡ en el puerto `8084`. Abre tu navegador web y ve a [http://localhost:8084](http://localhost:8084).

## API Key

La API key de DeepSeek estÃ¡ configurada en `src/main/resources/application.properties`.

```properties
deepseek.api.key=[tu API key de deepseek]
```

Para un entorno de producciÃ³n, se recomienda gestionar esta clave de forma mÃ¡s segura, por ejemplo, utilizando variables de entorno.
