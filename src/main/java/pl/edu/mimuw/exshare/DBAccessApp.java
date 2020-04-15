package pl.edu.mimuw.exshare;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DBAccessApp {

    public static void main(String[] args) {
        SpringApplication.run(DBAccessApp.class, args);
    }

}
