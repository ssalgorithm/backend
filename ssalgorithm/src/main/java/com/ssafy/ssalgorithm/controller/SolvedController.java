package com.ssafy.ssalgorithm.controller;

import com.ssafy.ssalgorithm.model.dto.SolvedDto;
import com.ssafy.ssalgorithm.model.service.SolvedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/solved")
public class SolvedController {

    @Autowired
    SolvedService service;

    @GetMapping("/test")
    public ResponseEntity<Map<String,String>> test() {
        Map<String, String> map  = new HashMap<String, String>();
        SolvedDto solvedDto = service.getSolved("1");
        String userId = solvedDto.getUserId();
        map.put("Solve User",userId);
        return new ResponseEntity<Map<String,String>>(map, HttpStatus.ACCEPTED);
    }
}
