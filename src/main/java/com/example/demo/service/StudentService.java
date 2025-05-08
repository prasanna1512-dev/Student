package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.demo.Exception.StudentNotFoundException;
import com.example.demo.FeignClient.AddressClient;
import com.example.demo.Response.AddressResponse;
import com.example.demo.Response.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.repository.Studentrepo;


@Service
public class StudentService {
	@Autowired
    private Studentrepo sdtrepo;
	
	@Autowired
    private AddressClient addressClient;
	
	 @Autowired
		private ModelMapper mapper;
	
	private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    public List<Student> getAllStudents() {
    	log.info("Retrieving all students");
        return   sdtrepo.findAll();
    }

   
    public StudentResponse getStudentByRollno(int rollno) {

        Optional<Student> student  = sdtrepo.findById(rollno);
        StudentResponse  stdResponse = mapper.map(student, StudentResponse.class);

        // Using FeignClient
        ResponseEntity<AddressResponse> addressResponse = addressClient.getAddressByStudentRollno(rollno);
        stdResponse.setAddressResponse(addressResponse.getBody());

        return stdResponse;
    }


    public Student addStudent(Student student) {
    	log.info("Adding new student: {}", student);
        return sdtrepo.save(student);
    }

    public void removeStudent(int rollno) {
    	log.info("Attempting to delete student with rollno {}", rollno);
        if (!sdtrepo.existsById(rollno)) {
            log.warn("Cannot delete, student with rollno {} not found", rollno);
            throw new StudentNotFoundException("Student with rollno " + rollno + " not found");
        }
        sdtrepo.deleteById(rollno);
        log.info("Deleted student with rollno {}", rollno);
    }  
        
    public Student updateStudent(int rollno, Student studentDetails) {
    	log.info("Updating student with rollno {}", rollno);
        Student student = sdtrepo.findById(rollno)
                .orElseThrow(() -> {
                    log.warn("Student with rollno {} not found for update", rollno);
                    return new StudentNotFoundException("Student with rollno " + rollno + " not found");
                });

        student.setName(studentDetails.getName());
        student.setDepartment(studentDetails.getDepartment());
        log.info("Updated student: {}", student);
        return sdtrepo.save(student);
    }
	
	

}
