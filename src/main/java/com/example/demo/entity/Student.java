package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int rollno;
	@Column(name = "studentname")
	public String name;
	@Column(name = "stdepartment")
	public String department;
	@Column
	public float percentage;

	public String getName() {
		return name;
	}

	public Student() {

	}

	public Student(String name, String department, float percentage) {
		super();
		this.name = name;
		this.department = department;
		this.percentage = percentage;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + ", department=" + department + ", percentage="
				+ percentage + "]";
	}
}
