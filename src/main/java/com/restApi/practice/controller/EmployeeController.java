package com.restApi.practice.controller;

import com.restApi.practice.model.Employee;
import com.restApi.practice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    //Build Create Employee Method in REST
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){

        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // Build Get Employee Method In REST

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployee();

    }

    //Build GET Employee by ID

    //http://localhost:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);

    }

    //Build UPDATE Employee in REST
    //http://localhost:8080/api/employees/1
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee){

        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id), HttpStatus.OK);


    }

    //Build DELETE Employee in REST
   @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee Deleted Successfully", HttpStatus.OK);

    }





    }
