package br.com.adriane.demo.reactivecustomrepository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends ReactiveCrudRepository<User, Integer> {
}
