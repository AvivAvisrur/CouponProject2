package com.project2.project2;

import com.project2.project2.Util.Art;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project2Application {

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
        System.out.println(Art.localhost);
    }
}
