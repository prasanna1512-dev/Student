package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
 
import java.util.Arrays;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
@WebMvcTest(Controller.class)
class demoRestControllerTest {
      
	@Autowired
    private MockMvc mockMvc;
 
	@MockBean
    private StudentService sdtService;
 
    @InjectMocks
    private Controller controller;
    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
 
    @Test
    void testGetAllStudents() throws Exception {
        Student student = new Student("hanvi","EEE",78.9f);
        when(sdtService.getAllStudents()).thenReturn(Arrays.asList(student));
 
        mockMvc.perform(get("/students"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("hanvi"));
    }
 
    @Test
    void testGetStudent() throws Exception {
        Student student = new Student("hanvi","EEE",78.9f);
        when(sdtService.getStudentById(1)).thenReturn(Optional.of(student));
 
        mockMvc.perform(get("/student/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("hanvi"));
    }
 
    @Test
    void testAddStudent() throws Exception {
        Student student = new Student("John Smith", "Mechanical",67.9f);
        when(sdtService.addStudent(any(Student.class))).thenReturn(student);
 
        mockMvc.perform(post("/student/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(student)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("John Smith"));
    }
 
    @Test
    void testUpdateStudent() throws Exception {
        Student updatedStudent = new Student("John Smith", "Mechanical",67.9f);
        when(sdtService.updateStudent(eq(1), any(Student.class))).thenReturn(updatedStudent);
 
        mockMvc.perform(put("/student/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedStudent)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John Smith"));
    }
 
    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(sdtService).removeStudent(7);
 
        mockMvc.perform(delete("/student/delete/7"))
            .andExpect(status().isOk());
    }
}
 

	


