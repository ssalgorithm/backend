package com.ssafy.ssalgorithm.user.controller;

import com.ssafy.ssalgorithm.user.model.dto.UserDto;
import com.ssafy.ssalgorithm.user.model.service.JwtService;
import com.ssafy.ssalgorithm.user.model.service.JwtServiceImpl;
import com.ssafy.ssalgorithm.user.model.service.UserService;
import com.ssafy.ssalgorithm.user.model.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final JwtService jwtService;

    // lombok - RequiredArgsConstructor 로 대체
//    @Autowired
//    public UserController(UserServiceImpl userService, JwtServiceImpl jwtService) {
//        this.userService = userService;
//        this.jwtService = jwtService;
//    }
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserDto userDto){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        //log
        logger.info("{}", userDto);
        try{
            userService.join(userDto);

            resultMap.put("message","Join Success");
            status = HttpStatus.OK;

        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("message","Join Error");
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        try{
            UserDto loginUser = userService.login(userDto);
            if(loginUser != null){
                //access-token 재 발급
                String accessToken = jwtService.createAccessToken(loginUser);

                //refresh-token 재 발급
                String refreshToken = jwtService.createRefreshToken(loginUser);

                // resultMap 에 토큰 저장
                resultMap.put("message", "success");
                resultMap.put("access-token", accessToken);
                resultMap.put("refresh-token", refreshToken);

                status = HttpStatus.OK;

            }else{
                //비밀 번호 매치 실패
                resultMap.put("message","fail");
                status = HttpStatus.NO_CONTENT; //204
            }
        }catch (Exception e){
            // 에러
            resultMap.put("message","fail");
            status = HttpStatus.UNAUTHORIZED; //401
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody UserDto userDto, HttpServletRequest request) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        //refresh Token을 이용하여 access Token을 재 발급받는다.
        String token = request.getHeader("refresh-token");

        if(jwtService.checkToken(token)){
            //리프레쉬 토큰 check ok!!
            // userID 의 token 과 비교
            if(token.equals(userService.getRefreshToken(userDto))){
                String accessToken = jwtService.createAccessToken(userDto);

                resultMap.put("access-token", accessToken);
                resultMap.put("message", "success");
                status = HttpStatus.OK;
            }
        }else{
            // 리프레쉬 토큰도 사용 불가 - 재 로그인
            resultMap.put("message","리프레쉬 토큰 사용불가");
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    @PostMapping("/info")
    public ResponseEntity<?> getInfo(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        // header에 담긴 token 꺼내기
        String token = request.getHeader("access-token");

        //check token
        boolean isValid = jwtService.checkToken(token);

        if(isValid){    // 유효한 토큰??
            try{
                String userId = jwtService.parseToken(token);
                resultMap.put("userId", userId);
                status = HttpStatus.OK;
            }catch (Exception e) {
                resultMap.put("message", "No user");
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }else{// 유효하지 않은 토큰
            // 토큰 재발급
            resultMap.put("message", "fail");
            status = HttpStatus.UNAUTHORIZED;   // 401

        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
