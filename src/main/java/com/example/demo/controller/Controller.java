package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Response.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
public class Controller {
	@Autowired
	StudentService sdtService;
	 private static final Logger log = LoggerFactory.getLogger(StudentService.class);
		@GetMapping("/students")
		public List<Student> getAllStudents() {
			log.info("GET /student - Fetching student");
			return sdtService.getAllStudents();
		}

		@GetMapping("/student/{rollno}")
	    private ResponseEntity<StudentResponse> getEmployeeDetails(@PathVariable("rollno") int rollno) {
			StudentResponse student = sdtService.getStudentByRollno(rollno);
	        return ResponseEntity.status(HttpStatus.OK).body(student);
	    }

		@PostMapping("/student/add")
		@ResponseStatus(code = HttpStatus.CREATED)
		public Student addStudent(@RequestBody Student student) {
			log.info("POST /student/add - Adding student: {}", student);
			return sdtService.addStudent(student);
		}

		@PutMapping("/student/update/{rollno}")
		public Student updateStudent(@PathVariable int rollno,@RequestBody Student studentDetails) {
			log.info("PUT /student/update/{} - Updating student with details: {}", rollno, studentDetails);
			return  sdtService.updateStudent(rollno, studentDetails);
		}

		@DeleteMapping("/student/delete/{id}")
		@ResponseStatus(code = HttpStatus.OK)
		public void deleteStudent(@PathVariable("id") int rollno) {
			log.info("DELETE /student/delete/{} - Deleting student", rollno);
			sdtService.removeStudent(rollno);
			
		}

}
