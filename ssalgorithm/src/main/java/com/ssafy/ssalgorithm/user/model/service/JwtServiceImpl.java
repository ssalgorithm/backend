package com.ssafy.ssalgorithm.user.model.service;

import com.ssafy.ssalgorithm.user.model.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService{
    private static final String SALT = "Ssalgorithm";
    @Override
    public String createAccessToken(UserDto userDto) {
        //1000ms * 30(m) * 60(s)
        return createToken("userId", userDto.getUserId(), "Access-Token", 1000 * 60 * 30 * 1);
    }

    @Override
    public String createRefreshToken(UserDto userDto) {
        //1000ms * 60(m) * 60(s) * 24(H) * 7(week) = 2 weeks
        return createToken("userId", userDto.getUserId(), "Refresh-Token", 1000 * 60 * 60 * 24 * 7 * 2);
    }

    @Override
    public <T> String createToken(String key, T data, String subject, long expire_time) {
        String jwt = Jwts.builder()
                // Header 설정
                .setHeaderParam("typ","JWT")
                .setHeaderParam("regDate",System.currentTimeMillis())

                // Payload 설정
                .setExpiration(new Date(System.currentTimeMillis() + expire_time))
                .setSubject(subject)
                .claim(key, data)

                //Signature 설정 : secret Key를 활용한 암호화
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact(); //직렬화 처리.

        return jwt;
    }

    @Override
    public boolean checkToken(String token) {
        return false;
    }

    @Override
    public String parseToken(String token) {
        return null;
    }
    private byte[] generateKey(){
        byte[] key = null;
        try{
            key =SALT.getBytes("UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return key;
    }
}
