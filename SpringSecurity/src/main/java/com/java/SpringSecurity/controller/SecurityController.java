package com.java.SpringSecurity.controller;

import com.java.SpringSecurity.security.SecurityConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SecurityController {


    @PreAuthorize("hasRole('MAKER')")
    @GetMapping("/order/getOrderDetails")
    public String helloUser(){
     log.info("User Details fetched for User");
     return "Order Details are fetched. ";
    }
}
