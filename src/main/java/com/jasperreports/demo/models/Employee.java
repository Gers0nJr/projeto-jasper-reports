package com.jasperreports.demo.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="EMPLOYEES")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLOYEE_ID")
	private Long employeeId;

	@Column(name="COMMISSION_PCT")
	private BigDecimal commissionPct;

	@Column(name="DEPARTMENT_ID")
	private BigDecimal departmentId;

	private String email;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Temporal(TemporalType.DATE)
	@Column(name="HIRE_DATE")
	private Date hireDate;

	@Column(name="JOB_ID")
	private String jobId;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="PHONE_NUMBER")
	private String phoneNumber;

	private BigDecimal salary;

	public Employee() {
	}
}