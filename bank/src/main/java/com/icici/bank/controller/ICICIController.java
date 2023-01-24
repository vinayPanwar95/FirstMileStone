package com.icici.bank.controller;

import com.icici.bank.entity.ICICIEntity;
import com.icici.bank.model.ICICIRequestDTO;
import com.icici.bank.service.ICICIService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ICICIController {
   @Autowired
   ICICIService iciciService;

   @PostMapping("/createLVX")
    public ICICIEntity createLVX (ICICIRequestDTO requestDTO){
        return  iciciService.createLVX(requestDTO);
    }
}
