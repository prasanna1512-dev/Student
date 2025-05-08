package com.example.demo.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "std")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public int rollno;
	@Column(name = "name")
	public String name;
	@Column(name = "department")
	public String department;
	public float percentage;
	
	
	public Student( String name, String department, float percentage) {
		super();
		this.name = name;
		this.department = department;
		this.percentage = percentage;
	}

}
