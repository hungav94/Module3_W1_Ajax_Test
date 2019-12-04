package com.codegym.service;

import com.codegym.model.Department;

public interface DepartmentService {

    Iterable<Department> findAll();

    Department findById(Long id);

    Department save(Department department);

    Department delete(Long id);
}
