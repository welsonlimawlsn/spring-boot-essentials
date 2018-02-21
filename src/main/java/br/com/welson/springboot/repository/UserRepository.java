package br.com.welson.springboot.repository;

import br.com.welson.springboot.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);
}
