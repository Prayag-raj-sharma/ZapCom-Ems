package com.ems.ems.project.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ems.ems.project.model.Employee;
import com.ems.ems.project.model.EmployeePage;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long>, JpaRepository<Employee, Long> {

	

}
