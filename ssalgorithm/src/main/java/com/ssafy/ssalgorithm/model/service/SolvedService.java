package com.ssafy.ssalgorithm.model.service;

import com.ssafy.ssalgorithm.model.dao.SolvedMapper;
import com.ssafy.ssalgorithm.model.dto.SolvedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolvedService {

    @Autowired
    SolvedMapper dao;

    public SolvedDto getSolved(String no) {
        SolvedDto dto = dao.selectOne(no);
        return dto;
    }
}
