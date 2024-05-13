package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entity.Employees;
import spring.services.ServiceEmp;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private ServiceEmp<Employees> employeesService;
    @GetMapping("/")
    public String showAll(Model model){

        List<Employees> employeesList=employeesService.getAll();
        model.addAttribute("employeesList",employeesList);
        return "main_page";
    }
    @GetMapping("/new")
    public String addEmployee(@ModelAttribute("newEmp")Employees employees){
        return "addPage";
    }

    @PostMapping("/new")
    public String addEmployeePost(@ModelAttribute("newEmp")Employees employees){
        employeesService.add(employees);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("empId") int id){
        employeesService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updatePage(@RequestParam("empId") int id, Model model){
        Employees employees=employeesService.getById(id);
        model.addAttribute("newEmp",employees);
        return "addPage";
    }

}
