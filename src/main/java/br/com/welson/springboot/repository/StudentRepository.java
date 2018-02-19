package br.com.welson.springboot.repository;

import br.com.welson.springboot.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByNameIgnoreCaseContaining(String name);
}
