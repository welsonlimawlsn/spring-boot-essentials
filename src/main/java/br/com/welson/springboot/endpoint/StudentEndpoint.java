package br.com.welson.springboot.endpoint;

import br.com.welson.springboot.error.CustomErrorType;
import br.com.welson.springboot.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(Student.getStudentList(), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        int index = Student.getStudentList().indexOf(new Student(id));
        if(index == -1)
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Student.getStudentList().get(index), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Student student) {
        Student.getStudentList().add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Student student) {
        Student.getStudentList().remove(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        Student.getStudentList().remove(student);
        Student.getStudentList().add(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
