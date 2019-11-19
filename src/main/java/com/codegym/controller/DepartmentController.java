package com.codegym.controller;

import com.codegym.model.Department;
import com.codegym.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public ModelAndView showListDepartment() {
        ModelAndView modelAndView = new ModelAndView("department/list");
        modelAndView.addObject("departments", departmentService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-department")
    public ModelAndView showCreateDepartment() {
        ModelAndView modelAndView = new ModelAndView("department/create");
        modelAndView.addObject("department", new Department());
        return modelAndView;
    }

    @PostMapping("/create-department")
    public ModelAndView saveDepartment(@ModelAttribute("department") Department department) {
        departmentService.save(department);
        ModelAndView modelAndView = new ModelAndView("department/create");
        modelAndView.addObject("department", new Department());
        modelAndView.addObject("message", "Create department successfully");
        return modelAndView;
    }

    @GetMapping("/edit-department/{id}")
    public ModelAndView showEditDepartment(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        ModelAndView modelAndView = null;
        if (department != null) {
            modelAndView = new ModelAndView("department/edit", "department", department);
        } else {
            modelAndView = new ModelAndView("error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit-department")
    public ModelAndView updateDepartment(@ModelAttribute("department") Department department) {
        departmentService.save(department);
        ModelAndView modelAndView = new ModelAndView("department/edit");
        modelAndView.addObject("department", department);
        modelAndView.addObject("message", "Edit department successfully");
        return modelAndView;
    }

    @GetMapping("/delete-department/{id}")
    public ModelAndView showDeleteDepartment(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        ModelAndView modelAndView = null;
        if (department != null) {
            modelAndView = new ModelAndView("department/delete", "department", department);
        } else {
            modelAndView = new ModelAndView("error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete-department")
    public String deleteDepartment(@ModelAttribute("department") Department department) {
        departmentService.delete(department.getId());
        return "redirect:/department";
    }

    @GetMapping("/view-department/{id}")
    public ModelAndView showViewDepartment(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        ModelAndView modelAndView = null;
        if (department != null) {
            modelAndView = new ModelAndView("department/view");
            modelAndView.addObject("department", department);
        } else {
            modelAndView = new ModelAndView("error-404");
        }
        return modelAndView;
    }
}
