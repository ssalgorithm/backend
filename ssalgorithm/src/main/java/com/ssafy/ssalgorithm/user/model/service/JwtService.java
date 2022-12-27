package com.ssafy.ssalgorithm.user.model.service;

import com.ssafy.ssalgorithm.user.model.dto.UserDto;

public interface JwtService {
    public String createAccessToken(UserDto userDto);
    public String createRefreshToken(UserDto userDto);
    public <T> String createToken(String key, T data, String subject, long expire_time);

    public boolean checkToken(String token);
    public String parseToken(String token);
}
