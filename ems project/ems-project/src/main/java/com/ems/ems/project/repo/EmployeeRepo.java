package com.ems.ems.project.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ems.ems.project.model.Employee;
import com.ems.ems.project.model.EmployeePage;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long>, JpaRepository<Employee, Long> {

	// To get the details of the employee by first name from the database.
	Employee getByfirstName(String firstName); 
	
	// To get the details of the employee by last name from the database.
	Employee getBylastName(String lastName);
	
	@Query("Select e from Employee e where e.firstName = ?1")
	List<Employee> getAllEmployeeByFirstName(String firstName);

	

}
