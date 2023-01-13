package com.example.demosecuritysignup.controller;



import com.example.demosecuritysignup.ds.Customer;
import com.example.demosecuritysignup.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("customer",new Customer());
        return "register";
    }
    @PostMapping("/register")
    public String saveCustomer(Customer customer, BindingResult result,
                               RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "register";
        }
        customerService.register(customer);
        redirectAttributes
                .addFlashAttribute("success",true);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("success",model
                .containsAttribute("success"));
        return "login";
    }
    @GetMapping({"/home","/"})
    public String home(){
        return "home";
    }
    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }
    @GetMapping("/main1")
    public String adminUserMain(){
        return "main1";
    }
    @GetMapping("/main2")
    public String adminMain(){
        return "main2";
    }
}
