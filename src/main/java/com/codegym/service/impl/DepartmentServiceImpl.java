package com.codegym.service.impl;

import com.codegym.model.Department;
import com.codegym.repository.DepartmentRepository;
import com.codegym.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Iterable<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findOne(id);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department delete(Long id) {
        Department department = findById(id);
        departmentRepository.delete(id);
        return department;
    }
}
