package com.postgre.SQLdemo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
           Student Nebojsa = new Student( "Nebojsa", "tane@gmail.com", LocalDate.of(1986, SEPTEMBER, 14));
            Student Lacika = new Student( "Lacika", "laci@gmail.com", LocalDate.of(1992, OCTOBER, 17));
            Student Maja = new Student("Maja", "majic@gmail.com", LocalDate.of(1995, AUGUST, 10));

            repository.saveAll(
                    List.of(Nebojsa, Lacika, Maja)
            );
        };
    }
}
