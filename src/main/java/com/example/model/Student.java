package com.example.model;

public class Student extends Person {
    private String studentId;


    public Student() {}

    public Student(String name, int age, String address, String studentId) {
        super(name, age, address);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{name='" + getName() + "', age=" + getAge() +
                ", address='" + getAddress() + "', studentId='" + studentId + "'}";
    }
}
