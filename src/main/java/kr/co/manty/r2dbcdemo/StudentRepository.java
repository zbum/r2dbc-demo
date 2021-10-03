package kr.co.manty.r2dbcdemo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

  @Repository
  public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {
    Mono<Void> deleteAll();
  }
