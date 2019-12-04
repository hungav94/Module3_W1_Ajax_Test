package com.codegym.controller;

import com.codegym.model.Department;
import com.codegym.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("")
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

    @PostMapping(value = "/create-department", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.save(department);
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

    @PostMapping(value = "/edit-department", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        department.setId(id);
        return departmentService.save(department);
    }

    @RequestMapping(value = "/delete-department/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Department showDeleteDepartment(@PathVariable Long id) {
        return departmentService.delete(id);
    }

//    @PostMapping("/delete-department")
//    public String deleteDepartment(@ModelAttribute("department") Department department) {
//        departmentService.delete(department.getId());
//        return "redirect:/department";
//    }

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
