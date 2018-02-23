package br.com.welson.springboot.javaclient;

import br.com.welson.springboot.handler.RestResponseExceptionHandler;
import br.com.welson.springboot.model.PageableResponse;
import br.com.welson.springboot.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class JavaClientDAO {
    private RestTemplate restTemplate = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1")
            .basicAuthorization("welsonlimawlsn", "123456789")
            .errorHandler(new RestResponseExceptionHandler())
            .build();

    public Student findById(long id) {
        return restTemplate.getForObject("/protected/students/{id}", Student.class, id);
    }

    public List<Student> listAll(String pageAndSort){
        return restTemplate.exchange("/protected/students/" + pageAndSort, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Student>>() {}).getBody().getContent();
    }

    public Student save(Student student){
        return restTemplate.exchange("/admin/students/", HttpMethod.POST,
                new HttpEntity<>(student, createJSONHeader()), Student.class).getBody();
    }

    public void update(Student student) {
        restTemplate.put("/admin/students/", student);
    }

    public void delete(long id) {
        restTemplate.delete("/admin/students/{id}", id);
    }

    private HttpHeaders createJSONHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
