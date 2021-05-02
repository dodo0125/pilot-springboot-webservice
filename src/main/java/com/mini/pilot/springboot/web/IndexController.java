package com.mini.pilot.springboot.web;

import com.mini.pilot.springboot.domain.sktservice.Sktservice;
import com.mini.pilot.springboot.service.CustomerService;
import com.mini.pilot.springboot.service.SktserviceService;
import com.mini.pilot.springboot.web.dto.CustomerListResponseDto;
import com.mini.pilot.springboot.web.dto.CustomerResponseDto;
import com.mini.pilot.springboot.web.dto.SktserviceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final CustomerService customerService;
    private final SktserviceService sktserviceService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("customer", customerService.findAllDesc());
        model.addAttribute("sktservice", sktserviceService.findAllDesc());
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


    @GetMapping("/customer/search")
    public String customerSearch(){
        return "customer-search";
    }

    @GetMapping("/customer/{customername}/search")
    public String customerSearchCustomername(@PathVariable String customername, Model model){
        model.addAttribute("customer",  customerService.findByName(customername));
        //model.addAttribute("customer", customerService.findAllDesc());
        return "customer-search";
    }


    @GetMapping("/sktservice/save")
    public String sktserviceSave() {
        return "sktservice-save";
    }

    @GetMapping("/sktservice/update/{id}")
    public String sktserviceUpdate(@PathVariable Long id, Model model){
        SktserviceResponseDto dto = sktserviceService.findById(id);
        model.addAttribute("sktservice", dto);

        return "sktservice-update" ;
    }


    @GetMapping("/customer/searchinfo")
    public String customerSearchinfo(){
        return "customer-search-info";
    }

    @GetMapping("/customer/{customername}/searchinfo")
    public String customerSearchinfoCustomername(@PathVariable String customername, Model model){
        model.addAttribute("customerinfo",  customerService.findCustomerinfoByName(customername));
        return "customer-search-info";
    }



}
