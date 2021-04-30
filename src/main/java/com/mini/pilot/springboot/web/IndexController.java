package com.mini.pilot.springboot.web;

import com.mini.pilot.springboot.service.CustomerService;
import com.mini.pilot.springboot.web.dto.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final CustomerService customerService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("customer", customerService.findAllDesc());
        return "index";
    }

    @GetMapping("/customer/save")
    public String customerSave(){
        return "customer-save";
    }

    @GetMapping("/customer/update/{id}")
    public String customerUpdate(@PathVariable Long id, Model model){
        CustomerResponseDto dto = customerService.findById(id);
        model.addAttribute("customer",dto);

        return "customer-update";
    }
}
