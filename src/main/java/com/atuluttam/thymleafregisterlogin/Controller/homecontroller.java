package com.atuluttam.thymleafregisterlogin.Controller;

import com.atuluttam.thymleafregisterlogin.Model.Employee;
import com.atuluttam.thymleafregisterlogin.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class homecontroller {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Home page
    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    // Registration page
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        // Add an empty Employee object to bind form data
        model.addAttribute("employee", new Employee());
        return "register";
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerEmployee(@ModelAttribute("employee") Employee employee) {
        // Save the employee data to the database
        employeeRepository.save(employee);
        return "welcome";
    }

    // Login page
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        // Add an empty Employee object to bind form data
        model.addAttribute("employee", new Employee());
        return "login";
    }

    // Handle login form submission
    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee != null && employee.getPassword().equals(password)) {
            model.addAttribute("employee", employee);
            return "welcome";
        } else {
            return "redirect:/login";
        }
    }

}
