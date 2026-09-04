package com.serviceRegistry.department_registry.controller;

import com.serviceRegistry.department_registry.entity.Department;
import com.serviceRegistry.department_registry.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
         Department department1=departmentService.createDepartement(department);
         return new ResponseEntity<>(department1, HttpStatus.CREATED);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long departmentId){
    Department department= departmentService.getDepartmentById(departmentId);
    return new ResponseEntity<>(department,HttpStatus.OK);

    }

    @GetMapping("/all")
    public List<Department> getALlDepartement(){
        return  departmentService.getAllDepartement();
    }

    @DeleteMapping("/departmentId")
    public void deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
    }
}
