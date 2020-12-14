package br.com.adrianerodrigues.junitdemo;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> searchUsers();

    User findById(Long id);
}
