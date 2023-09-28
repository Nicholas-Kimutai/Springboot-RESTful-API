package com.restApi.practice.service.impl;

import com.restApi.practice.exception.ResourceNotFoundException;
import com.restApi.practice.model.Employee;
import com.restApi.practice.repository.EmployeeRepository;
import com.restApi.practice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//       Optional <Employee> employee = employeeRepository.findById(id);
//        if (employee.isPresent()) {
//            return employee.get();
//
//        }
//        else{
//            throw new ResourceNotFoundException("Employee","Employee","id",id);
//        }
        return  employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Employee","id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // check whether the employee with existing ID exists or not

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("","Employee","id",id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        //save existing employee to database
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {

        //Check if the employee exists in the database or not
        employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("","message","id",id));
        employeeRepository.deleteById(id);
    }
}
