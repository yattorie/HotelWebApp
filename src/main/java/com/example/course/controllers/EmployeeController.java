package com.example.course.controllers;
import com.example.course.models.Employee;
import com.example.course.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/employee")
    public String employee(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee";
    }

    @GetMapping("/employee/add")
    public String employeeAdd(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        return "employee-add";
    }

    @PostMapping("/employee/add")
    public String employeePostAdd(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String middleName, @RequestParam String position,@RequestParam BigDecimal salary, @RequestParam String phoneEmployee,Model model) {
        Employee employees = new Employee(firstName, lastName, middleName, position, salary, phoneEmployee);
        employeeRepository.save(employees);
        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}")
    public String employeeDetails(@PathVariable(value = "id") long id, Model model) {
        if (!employeeRepository.existsById(id)) {
            return "redirect:/employee";
        }
        Optional<Employee> employee = employeeRepository.findById(id);
        ArrayList<Employee> esr = new ArrayList<>();
        employee.ifPresent(esr::add);
        model.addAttribute("employee", esr);
        return "employee-details";
    }

    @GetMapping("/employee/{id}/edit")
    public String employeeEdit(@PathVariable(value = "id") long id, Model model) {
        if (!employeeRepository.existsById(id)) {
            return "redirect:/employee";
        }
        Optional<Employee> employee = employeeRepository.findById(id);
        ArrayList<Employee> esr = new ArrayList<>();
        employee.ifPresent(esr::add);
        model.addAttribute("employee", esr);
        return "employee-edit";
    }

    @PostMapping("/employee/{id}/edit")
    public String employeePostUpdate(@PathVariable(value = "id") long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String middleName, @RequestParam String position,@RequestParam BigDecimal salary, @RequestParam String phoneEmployee, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setMiddleName(middleName);
        employee.setPosition(position);
        employee.setSalary(salary);
        employee.setPhoneEmployee(phoneEmployee);
        employeeRepository.save(employee);
        return "redirect:/employee";
    }
    @PostMapping("/employee/{id}/remove")
    public String employeePostDelete(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/employee";
    }

}
