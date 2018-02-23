package br.com.welson.springboot.javaclient;

import br.com.welson.springboot.model.PageableResponse;
import br.com.welson.springboot.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JavaSpringClientTest {
//    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplateBuilder()
//                .rootUri("http://localhost:8080/v1/protected/students")
//                .basicAuthorization("welsonlimawlsn","123456789")
//                .build();
//        Student student = restTemplate.getForObject("/{id}", Student.class, 4);
//        ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class, 4);
//        Student[] students = restTemplate.getForObject("/", Student[].class);
//        System.out.println(student);
//        System.out.println(forEntity.getBody());
//        System.out.println(Arrays.toString(students));
//
//        ResponseEntity<List<Student>> listResponseEntity = restTemplate
//                .exchange("/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {});
//        System.out.println(listResponseEntity.getBody());
//    }
    public static void main(String[] args) {

        JavaClientDAO javaClientDAO = new JavaClientDAO();

        System.out.println(javaClientDAO.findById(45));
        System.out.println(javaClientDAO.listAll("?sort=id,desc"));

        Student student = new Student();
        student.setName("Iraldo");
        student.setEmail("iraldoteles@gmail.com");

        System.out.println(javaClientDAO.save(student));
        System.out.println(javaClientDAO.listAll("?sort=id,desc"));

        Student welson = javaClientDAO.findById(1);
        welson.setEmail("welsonlimawlsn@gmail.com");
        javaClientDAO.update(welson);

        javaClientDAO.findById(111);

        //javaClientDAO.delete(4);


    }

    private static void get(){
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/protected/students")
                .basicAuthorization("welsonlimawlsn","123456789")
                .build();
        //Student student = restTemplate.getForObject("/{id}", Student.class, 4);
        //ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class, 4);
        ResponseEntity<PageableResponse<Student>> listResponseEntity = restTemplate
                .exchange("/?sort=id,desc", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Student>>() {});
        System.out.println(listResponseEntity);
        System.out.println(listResponseEntity.getBody().getContent());
    }
    private static void post(){
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/admin/students")
                .basicAuthorization("welsonlimawlsn", "123456789")
                .build();

        Student studentPost = new Student();
        studentPost.setName("Welson Teles");
        studentPost.setEmail("welson@simpatiabrasileira.com.br");

        ResponseEntity<Student> exchangePost = restTemplate.exchange("/", HttpMethod.POST, new HttpEntity<>(studentPost, createJSONHeader()), Student.class);
        System.out.println(exchangePost);
    }

    private static void post2(){
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/admin/students")
                .basicAuthorization("welsonlimawlsn", "123456789")
                .build();

        Student studentPost = new Student();
        studentPost.setName("Maria Jesus");
        studentPost.setEmail("mariajesus@gmail.com");
        //ResponseEntity<Student> postForEntity = restTemplate.postForEntity("/", studentPost, Student.class);
        Student postForObject = restTemplate.postForObject("/", studentPost, Student.class);
        System.out.println(postForObject);
    }

    private static HttpHeaders createJSONHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
