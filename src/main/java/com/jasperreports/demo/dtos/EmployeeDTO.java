package com.jasperreports.demo.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

	private String firstName;
	private Date hireDate;
	private String lastName;
	private String phoneNumber;
	private BigDecimal salary;
	
}
