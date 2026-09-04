package com.serviceRegistry.department_registry.service.serviceImpl;

import com.serviceRegistry.department_registry.entity.Department;
import com.serviceRegistry.department_registry.exception.ResourceNotFoundException;
import com.serviceRegistry.department_registry.repository.DepartmentRepository;
import com.serviceRegistry.department_registry.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department createDepartement(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("departmentId", "for this id", id));
    }

    @Override
    public List<Department> getAllDepartement() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("departmentId", "for this id", id));
        departmentRepository.delete(department);

    }
}
