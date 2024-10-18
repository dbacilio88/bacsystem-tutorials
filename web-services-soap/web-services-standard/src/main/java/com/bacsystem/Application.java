package com.bacsystem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 * <p>
 * Application class.
 * <p>
 * This class specifies the requirements for the Application component,
 * developed in accordance with the development standards established by bxcode.
 * Collaboration is encouraged for the enhancement and expansion of the api-microservice-application.
 *
 * @author bxcode
 * @author dbacilio88@outllok.es
 * @since 15/10/2024
 */
@SpringBootApplication(
        scanBasePackages = {
               "com.bacsystem",
                //"com.bacsystem.microservice.application",
                //"com.bacsystem.microservice.application"
        }
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}