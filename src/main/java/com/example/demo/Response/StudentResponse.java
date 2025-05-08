package com.example.demo.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
	public int rollno;
	public String name;
	public String department;
	public float percentage;
	
	private AddressResponse addressResponse;
}
