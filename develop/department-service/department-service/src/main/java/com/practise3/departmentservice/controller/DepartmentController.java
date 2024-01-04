package com.practise3.departmentservice.controller;

import com.practise3.departmentservice.client.EmployeeClient;
import com.practise3.departmentservice.model.Department;
import com.practise3.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private static  final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        logger.info("Adding a department :: {}", department);
        return departmentRepository.addDepartment(department);
    }


    @GetMapping
    public List<Department> findAll(){
        logger.info("fetching all departments");
        return  departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        logger.info("Department find :: id  {} ", id);
        return  departmentRepository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        logger.info("Department find with Employees");
        List<Department>  departments = departmentRepository.findAll();
        departments.forEach(department -> department.setEmployeeList(employeeClient.findByDepartmentId(department.getId())));
        return departments;
    }



}
