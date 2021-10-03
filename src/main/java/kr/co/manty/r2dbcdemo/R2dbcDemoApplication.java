package kr.co.manty.r2dbcdemo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Generated;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class R2dbcDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(R2dbcDemoApplication.class, args);
  }

  @Component
  @RequiredArgsConstructor
  class DataLoader {
    private final StudentRepository studentRepository;

    @PostConstruct
    private void load() {
      studentRepository
          .deleteAll()
          .thenMany(
              Flux.just("zbum", "manty", "dongmyo")
                  .map(Student::new)
                  .flatMap(studentRepository::save))
          .thenMany(studentRepository.findAll())
          .subscribe(System.out::println);
    }
  }

  @RestController
  @RequiredArgsConstructor
  class StudentController {
    private final StudentRepository repository;
    private final R2dbcEntityTemplate template;

    @GetMapping("/v1/students")
    public Flux<Student> getStudents(){
      return repository.findAll();
    }

    @GetMapping("/v2/students")
    public Flux<Student> getStudents2(){
      return template.select(Student.class)
              .all();
    }
  }

}
