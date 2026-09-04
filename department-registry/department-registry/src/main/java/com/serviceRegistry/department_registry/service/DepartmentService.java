package com.serviceRegistry.department_registry.service;

import com.serviceRegistry.department_registry.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartement(Department department);
    Department getDepartmentById(Long departmentId);
    List<Department> getAllDepartement();
    void deleteDepartment(Long id);
}
