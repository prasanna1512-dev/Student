package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.Studentrepo;
@Service
public class StudentService {
	@Autowired
    private Studentrepo sdtrepo;

    public List<Student> getAllStudents() {
        return  sdtrepo.findAll();
    }

    public Optional<Student> getStudentById(int rollno) {
        return sdtrepo.findById(rollno);
    }

    public Student addStudent(Student student) {
        return sdtrepo.save(student);
    }

    public void removeStudent(int rollno) {
    	sdtrepo.deleteById(rollno);
    }

    public Student updateStudent(int rollno, Student studentDetails) {
    	Student student = sdtrepo.findById(rollno).orElseThrow();
    	student.setName(studentDetails.getName());
        student.setDepartment(studentDetails.getDepartment());
        return sdtrepo.save(student);
    }
	
	

}
