package br.com.welson.springboot;

import br.com.welson.springboot.model.Student;
import br.com.welson.springboot.repository.StudentRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistData() {
        Student student = new Student("Welson", "welsonlimawlsn@gmail.com");
        studentRepository.save(student);
        assertThat(student.getId()).isNotNull();
        assertThat(student.getName()).isEqualTo("Welson");
        assertThat(student.getEmail()).isEqualTo("welsonlimawlsn@gmail.com");
    }

    @Test
    public void deleteShouldRemoveData() {
        Student student = new Student("Welson", "welsonlimawlsn@gmail.com");
        studentRepository.save(student);
        studentRepository.delete(student);
        assertThat(studentRepository.findOne(student.getId())).isNull();
    }

    @Test
    public void updateShouldChangeAndPersistData() {
        Student student = new Student("Welson", "welsonlimawlsn@gmail.com");
        studentRepository.save(student);
        student.setName("Welson2");
        student.setEmail("welsonlimawlsn@gmail.com");
        studentRepository.save(student);
        student = studentRepository.findOne(student.getId());
        assertThat(student.getName()).isEqualTo("Welson2");
        assertThat(student.getEmail()).isEqualTo("welsonlimawlsn@gmail.com");
    }

    @Test
    public void findByNameIgnoreCaseContainingShouldIgnoreCase() {
        Student student = new Student("Welson", "welsonlimawlsn@gmail.com");
        Student student2 = new Student("welson de lima", "welsonlimawlsns@gmail.com");
        studentRepository.save(student);
        studentRepository.save(student2);
        List<Student> students = studentRepository.findByNameIgnoreCaseContaining("welson");
        assertThat(students.size()).isEqualTo(2);
    }

//    @Test
//    public void createWhenNameIsNullShouldThrowConstraintViolarionException(){
//        thrown.expect(ConstraintViolationException.class);
//        thrown.expectMessage("The name field of the student is required");
//        studentRepository.save(new Student(null, "welsonlimawlsn@gmail.com"));
//    }
}
