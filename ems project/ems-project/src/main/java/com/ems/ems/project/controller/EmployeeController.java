//package com.ems.ems.project.controller;

/*import java.util.List;

import javax.ws.rs.BeanParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ems.ems.project.model.Employee;
import com.ems.ems.project.model.EmployeePage;
import com.ems.ems.project.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	
	public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public Page<Employee> getAllEmployees (
		   @RequestParam(defaultValue = "1") Integer pageNo,
          @RequestParam(defaultValue = "2") Integer pageSize,
          @RequestParam(defaultValue = "id") String sortBy ) {
      Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
      //EmployeePage page = new EmployeePage(pageNo, pageSize, sortBy);
      return service.getAllEmployees(pageable);
		
	}
//	@GetMapping
//	public List<Employee> getAllEmployees(
//			@RequestParam(defaultValue = "0") Integer pageNo,
//	           @RequestParam(defaultValue = "2") Integer pageSize,
//	           @RequestParam(defaultValue = "id") String sortBy ) {
//		EmployeePage page = new EmployeePage(pageNo, pageSize, sortBy);
//		return service.getAllEmployee(page);
//	}
	
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(service.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	@GetMapping({"/{id}"})
	public ResponseEntity<Employee> getEmployeeById(@Valid @PathVariable("id") long EmployeeId) {
		return new ResponseEntity<Employee>(service.getEmployeeById(EmployeeId), HttpStatus.OK);
	}

	@PutMapping({"/{id}"})
	public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable("id") long id, @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(service.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	@DeleteMapping({"/{id}"})
	public ResponseEntity<String> deleteEmployee(@Valid @PathVariable("id") long id) {
		
		service.deleteEmployee(id);
		return new ResponseEntity<String>("Employee Deleted", HttpStatus.OK);
	}
	
}







@RestController
@RequestMapping("api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @BeanParam Employee employee) {
		Employee savedEmpl = service.saveEmployee(employee);
		return new ResponseEntity<>(savedEmpl, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Page<Employee>> getAllEmployees(@BeanParam EmployeePage employeePage) {
		Pageable pageable = PageRequest.of(employeePage.getPageNumber(), employeePage.getPageSize(),
			Sort.by(employeePage.getSortDirection(), employeePage.getSortBy()));
		Page<Employee> employees = service.getAllEmployees(pageable);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
		Employee employee = service.getEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @BeanParam Employee employee, @PathVariable("id") long id) {
		Employee updatedEmpl = service.updateEmployee(employee, id);
		return new ResponseEntity<>(updatedEmpl, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long id) {
		service.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}*/







package com.ems.ems.project.controller;

import com.ems.ems.project.model.Employee;
import com.ems.ems.project.model.EmployeePage;
import com.ems.ems.project.service.EmployeeService;
import jakarta.validation.Valid;

import javax.ws.rs.BeanParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Employee> getAllEmployees(@BeanParam EmployeePage page) {
        Pageable pageable = page.toPageable();
        return service.getAllEmployees(pageable);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(service.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@Valid @PathVariable("id") long employeeId) {
        return new ResponseEntity<>(service.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable("id") long id, @RequestBody Employee employee) {
        return new ResponseEntity<>(service.updateEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@Valid @PathVariable("id") long id) {
        service.deleteEmployee(id);
        return new ResponseEntity<>("Employee Deleted", HttpStatus.OK);
    }

}

