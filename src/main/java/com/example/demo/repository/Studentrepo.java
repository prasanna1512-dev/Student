package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Student;

public interface Studentrepo extends JpaRepository<Student,Integer> {

}
