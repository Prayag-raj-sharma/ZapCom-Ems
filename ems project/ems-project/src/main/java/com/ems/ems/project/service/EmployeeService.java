package com.ems.ems.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.ems.ems.project.model.Employee;
import com.ems.ems.project.model.EmployeePage;


public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	//Page<Employee> getAllEmployees(EmployeePage page);
	Employee getEmployeeById(long id);
	Employee updateEmployee(Employee employee, long id);
	void deleteEmployee(long id);
	//List<Employee> getAllEmployee(EmployeePage page);
	Page<Employee> getAllEmployees(Pageable pageable);
	
    
	

}
