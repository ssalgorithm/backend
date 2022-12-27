package com.ssafy.ssalgorithm.user.model.service;

import com.ssafy.ssalgorithm.user.model.dto.UserDto;

import java.sql.SQLException;

public interface UserService {
    public UserDto login(UserDto userDto) throws Exception;
    public String getRefreshToken(UserDto userDto) throws SQLException;

}
