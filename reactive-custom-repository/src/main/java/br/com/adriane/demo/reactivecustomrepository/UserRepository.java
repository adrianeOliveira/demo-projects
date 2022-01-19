package br.com.adriane.demo.reactivecustomrepository;

import java.util.List;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository  extends ReactiveCrudRepository<User, Integer> {

    @Query("select id, name from users where id = :id")
    Mono<UserCustom> findUserCustom(@Param("id") Integer id);

    @Query("select id, name from users")
    Mono<List<UserCustom>> findAllUserCustom();

    @Query("select * from users")
    Mono<List<User>> findAllUsers();
}
