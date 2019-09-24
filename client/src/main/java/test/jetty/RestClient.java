package test.jetty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestClient
{
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestClient.class);
        app.run(args);
    }
}
