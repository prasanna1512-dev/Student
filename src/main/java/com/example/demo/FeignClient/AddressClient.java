package com.example.demo.FeignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Response.AddressResponse;

@FeignClient(name = "studentaddressservice", url = "http://localhost:8082", path = "/studentaddressservice")
public interface AddressClient {

    @GetMapping("/address/{rollno}")
    public ResponseEntity<AddressResponse> getAddressByStudentRollno(@PathVariable("rollno") int rollno);

}