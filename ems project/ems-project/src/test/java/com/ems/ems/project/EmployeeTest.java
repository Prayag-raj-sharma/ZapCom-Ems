/*package com.ems.ems.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ems.ems.project.model.Employee;
import com.ems.ems.project.model.EmployeePage;
import com.ems.ems.project.repo.EmployeeRepo;
import com.ems.ems.project.service.EmployeeService;
import com.ems.ems.project.service.impl.EmployeeServiceImpl;



class EmployeeTest {
    
    @Mock
    private EmployeeRepo repo;
    
    public EmployeeTest() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    void testGetEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"john@gmail.com","John", "Cena"));
        employees.add(new Employee(2,"jane@gmail.com","Jane", "Cena"));
        Page<Employee> page = new PageImpl<>(employees, PageRequest.of(0, 2), employees.size());
        EmployeePage employeePage = new EmployeePage();
        employeePage.setPageSize(2);
        employeePage.setPageNumber(2);
        employeePage.setSortBy("id");
        //employeePage.setSortDirection(Sort.Direction.ASC);
        when(repo.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "id"))))
            .thenReturn(page);
        EmployeeServiceImpl service = new EmployeeServiceImpl(repo);
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC));
        Page<Employee> result = service.getAllEmployees(pageable);
        //assertEquals(0, result.getContent().size());
        //assertEquals(0, result.getTotalElements());
        assertEquals("John", result.getContent().get(0).getFirstName());
        assertEquals("Cena", result.getContent().get(1).getLastName());
    }
    
    @Test
    void testAddEmployee() {
        Employee employee = new Employee();
        employee.setId(10);
        employee.setEmailId("john@gmail.com");
        employee.setFirstName("John");
        employee.setLastName("Cena");
        when(repo.save(employee)).thenReturn(employee);
        EmployeeServiceImpl service = new EmployeeServiceImpl(repo);
        Employee result = service.saveEmployee(employee);
        assertEquals("John", result.getFirstName());
        assertEquals("Cena", result.getLastName());
        assertEquals("john@gmail.com", result.getEmailId());
        assertEquals(10, result.getId());
    
    }
}*/



package com.ems.ems.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ems.ems.project.controller.EmployeeController;
import com.ems.ems.project.model.Employee;
import com.ems.ems.project.model.EmployeePage;
import com.ems.ems.project.repo.EmployeeRepo;
import com.ems.ems.project.service.EmployeeService;
import com.ems.ems.project.service.impl.EmployeeServiceImpl;

public class EmployeeTest {

    @Mock
    private EmployeeService employeeService;
 

    @InjectMocks
    private EmployeeController employeeController;
    

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        // Create a list of employees for testing
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", "john.doe@example.com"));
        employees.add(new Employee(2L, "Jane", "Doe", "jane.doe@example.com"));

        // Create a page of employees for testing
        Page<Employee> employeePage = new PageImpl<>(employees, PageRequest.of(0, 2, Sort.by("id")), 2);
        
        EmployeePage page = new EmployeePage(1,2,"id");

        // Mock the service method to return the page of employees
        when(employeeService.getAllEmployees(PageRequest.of(1, 2, Sort.by("id")))).thenReturn(employeePage);
        
        

        // Call the controller method and check the response
        Page<Employee> response = employeeController.getAllEmployees(page);
        assertEquals(employeePage, response);

        // Verify that the service method was called once with the correct arguments
        verify(employeeService, times(1)).getAllEmployees(PageRequest.of(1, 2, Sort.by("id")));
        
       
    }

    @Test
    void testSaveEmployee() {
        // Create an employee for testing
        Employee employee = new Employee(1L, "John", "Cena", "john@gmail.com");

        // Mock the service method to return the saved employee
        when(employeeService.saveEmployee(employee)).thenReturn(employee);

        // Call the controller method and check the response
        ResponseEntity<Employee> response = employeeController.saveEmployee(employee);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employee, response.getBody());

        // Verify that the service method was called once with the correct argument
        verify(employeeService, times(1)).saveEmployee(employee);
    }

   
    
    @Test
    void deleteEmployee() {
       
        ResponseEntity<String> response = employeeController.deleteEmployee(1L);

       
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee Deleted", response.getBody());
        verify(employeeService, times(1)).deleteEmployee(anyLong());
    }
    
    @Test
    void getEmployeeById() {
        
        Employee employee = new Employee(1L, "John", "Cena", "john@gmail.com");
        when(employeeService.getEmployeeById(anyLong())).thenReturn(employee);

        
        ResponseEntity<Employee> response = employeeController.getEmployeeById(1L);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
        verify(employeeService, times(1)).getEmployeeById(anyLong());
    }
    
    @Test
    void getEmployeeByFirstName() {
        
        Employee employee = new Employee(1L, "John", "Cena", "john@gmail.com");
        when(employeeService.getEmployeeByFirstName(any())).thenReturn(employee);

        
        ResponseEntity<Employee> response = employeeController.getEmployeeByFirstName("John");

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
        verify(employeeService, times(1)).getEmployeeByFirstName(any());
    }
    
    @Test
    void getEmployeeByLastName() {
        
        Employee employee = new Employee(1L, "John", "Cena", "john@gmail.com");
        when(employeeService.getEmployeeByLastName(any())).thenReturn(employee);

        
        ResponseEntity<Employee> response = employeeController.getEmployeeByLastName("Cena");

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
        verify(employeeService, times(1)).getEmployeeByLastName(any());
    }

    @Test
    void updateEmployee() {
        
        Employee employee = new Employee(1L, "John", "Cena", "john@gmail.com");
        when(employeeService.updateEmployee(any(Employee.class), anyLong())).thenReturn(employee);

        
        ResponseEntity<Employee> response = employeeController.updateEmployee(1L, employee);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
        verify(employeeService, times(1)).updateEmployee(any(Employee.class), anyLong());
    }
    
    
   
}






