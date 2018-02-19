package br.com.welson.springboot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class Student {

    private int id;
    private String name;
    private static List<Student> studentList;

    static {
        studentRepository();
    }

    public Student() {
    }

    public Student(int id) {
        this.id = id;
    }

    public Student(int id, String name) {
        this(id);
        this.name = name;
    }

    private static void studentRepository() {
        studentList = new ArrayList<>(asList(
                new Student(1, "Welson"),
                new Student(2, "Wilson"),
                new Student(3, "Maria"),
                new Student(4, "Iraldo"),
                new Student(5, "Miguel")
        ));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
