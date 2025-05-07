package com.example.demo.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
	public int rollno;
	public String name;
	public String department;
	public float percentage;
	
	private AddressResponse addressResponse;
}
