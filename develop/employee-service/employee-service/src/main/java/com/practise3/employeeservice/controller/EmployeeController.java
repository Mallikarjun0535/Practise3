package com.practise3.employeeservice.controller;


import com.practise3.employeeservice.model.Employee;
import com.practise3.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping
    public Employee addEmployee(@RequestBody  Employee employee) {
        logger.info("Employee add : id :: {}", employee);
        return employeeRepository.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> getEmployees() {

        logger.info("fetch all Employees");
        return employeeRepository.getEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {

        logger.info("Find by employee  : id ::{}", id);
        return employeeRepository.findByEmployee(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable Long departmentId) {

        logger.info("Find by employees in the department  : id ::{}", departmentId);
        return employeeRepository.findByDepartment(departmentId);
    }


}
