package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
public class Controller {
	@Autowired
	StudentService sdtService;

		@GetMapping("/students")
		public List<Student> getAllStudents() {
			return sdtService.getAllStudents();
		}

		@GetMapping("/student/{rollno}")
		public Student getStudent(@PathVariable int rollno) {
			return sdtService.getStudentById(rollno).orElseThrow();
		}

		@PostMapping("/student/add")
		@ResponseStatus(code = HttpStatus.CREATED)
		public Student addStudent(@RequestBody Student student) {
			return sdtService.addStudent(student);
		}

		@PutMapping("/student/update/{rollno}")
		public Student updateStudent(@PathVariable int rollno,@RequestBody Student studentDetails) {
			return  sdtService.updateStudent(rollno, studentDetails);
		}

		@DeleteMapping("/student/delete/{id}")
		@ResponseStatus(code = HttpStatus.OK)
		public void deleteStudent(@PathVariable("id") int rollno) {
			sdtService.removeStudent(rollno);
			
		}

}
