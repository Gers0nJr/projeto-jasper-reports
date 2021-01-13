package com.jasperreports.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasperreports.demo.models.Employee;
import com.jasperreports.demo.repositories.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping
	public ResponseEntity<List<Employee>> findByEmployee(){
		try {
			return ResponseEntity.ok().body(employeeRepository.findAll());
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
}
