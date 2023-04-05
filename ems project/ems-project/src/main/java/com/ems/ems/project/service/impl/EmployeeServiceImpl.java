package com.ems.ems.project.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ems.ems.project.exception.ErrorDetails;
import com.ems.ems.project.exception.ResourceNotFoundException;
import com.ems.ems.project.model.Employee;
import com.ems.ems.project.model.EmployeePage;
import com.ems.ems.project.repo.EmployeeRepo;
import com.ems.ems.project.service.EmployeeService;

@Service
@Primary
public class EmployeeServiceImpl implements EmployeeService{
	
    @Autowired
	private EmployeeRepo repo;
	//private JdbcTemplate jdbcTemplate;
	
	ErrorDetails ed = new ErrorDetails();
	
	//@Autowired
	public EmployeeServiceImpl(EmployeeRepo repo) {
		super();
		this.repo = repo;
	}
	
	//@Autowired
//	public EmployeeServiceImpl(JdbcTemplate jdbcTemplate) {
//		super();
//		this.jdbcTemplate = jdbcTemplate;
//	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);
	}
	
	@Override
	public Page<Employee> getAllEmployees(Pageable pageable) {
        return repo.findAll(pageable);
	}

	/*@Override
	public List<Employee> getAllEmployee(EmployeePage page) {
		 
		
		int pageSize = page.getPageSize();
		int pageNo = page.getPageNumber();
		int offset = (pageNo - 1) * pageSize;
		
		String sql = "SELECT * FROM employees ORDER BY" + page.getSortBy() +" " + page.getSortDirection()
		+ "LIMIT ? OFFSET ?";
		
		List<Employee> employee = jdbcTemplate.query(sql,new Object[]{pageSize,offset}, new EmployeeRowMapper());
		
		return employee;
	}
	
	public static final class EmployeeRowMapper implements RowMapper<Employee> {
    	 @Override
         public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
             Employee employee = new Employee();
             employee.setId(rs.getLong("id"));
             employee.setEmailId(rs.getString("e_mail"));
             employee.setFirstName(rs.getString("first_name"));
             employee.setLastName(rs.getString("last_name"));
             return employee;
         }

    } */

	
	

	@Override
	public Employee getEmployeeById(long id) {
		return repo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException(ed.getMessage()));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
				Employee existingEmployee = repo.findById(id).orElseThrow(
						() -> new ResourceNotFoundException(ed.getMessage())); 
				
				existingEmployee.setEmailId(employee.getEmailId());
				existingEmployee.setFirstName(employee.getFirstName());
				existingEmployee.setLastName(employee.getLastName());
				
				// save existing employee to database
				repo.save(existingEmployee);
				return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		repo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException(ed.getMessage()));
        repo.deleteById(id);
		
	}

}