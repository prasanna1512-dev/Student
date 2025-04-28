package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Student;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class StudentRepositoryTest {

	@BeforeEach
	void setUp() throws Exception {
	}


	    @Autowired
	    private Studentrepo sdtrepo;

	    @Test
	    public void testSaveEmployee() {
	        Student student= new Student("hanvi","EEE",78.9f);
	        Student savedStudent = sdtrepo.save(student);
	        assertNotNull(savedStudent);
	        assertEquals("hanvi", savedStudent.getName());
	    }

	    @Test
	    public void testGetEmployee() {
	    	 Student student= new Student("hanvi","EEE",78.9f);
	    	 sdtrepo.save(student);
	    	 Student fetchedStudent =sdtrepo.findById(student.getRollno()).orElse(null);
	         assertNotNull(fetchedStudent);
	        assertEquals(student.getRollno(), fetchedStudent.getRollno());
	    }

	    @Test
	    public void testGetListOfEmployees() {
	    	sdtrepo.save(new Student("abc","ece",32.0f));
	        List<Student> students = sdtrepo.findAll();
	        assertNotNull(students);
	        assertEquals(7, students.size());
	    }

	    @Test
	    public void testUpdateEmployee() {
	    	Student student= sdtrepo.save(new Student("abc","ece",32.0f));
	    	student.setName("John Smith");
	    	sdtrepo.save(student);
	    	Student updatedStudent = sdtrepo.findById(student.getRollno()).orElse(null);
	        assertNotNull( updatedStudent);
	        assertEquals("John Smith",  updatedStudent.getName());
	    }

	    @Test
	    public void testDeleteEmployee() {
	    	Student student = sdtrepo.save(new Student("abc","ece",32.0f));
	    	sdtrepo.deleteById(student.getRollno());
	    	Student deletedStudent= sdtrepo.findById(student.getRollno()).orElse(null);
	        assertNull(deletedStudent);
	    }
	}
