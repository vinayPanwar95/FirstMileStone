package com.icici.bank.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icici.bank.entity.ICICIEntity;
//import com.icici.bank.mapper.ICICIMapper;
import com.icici.bank.model.ICICIRequestDTO;
import com.icici.bank.repo.ICICIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ICICIService {

    private final ICICIRepository iciciRepo;

//    private ICICIMapper mapper;
    public ICICIEntity createLVX(ICICIRequestDTO requestDTO) {

//        return iciciRepo.save(mapper.fromICICIRequestDTOToEntity(requestDTO));
        ICICIEntity iciciEntity = new ICICIEntity().id(requestDTO.getId()).version(requestDTO.getVersion()).lvPercentage(requestDTO.getLvPercentage());
        return iciciRepo.save(iciciEntity);
    }
}
