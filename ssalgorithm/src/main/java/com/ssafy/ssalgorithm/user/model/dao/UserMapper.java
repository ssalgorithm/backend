package com.ssafy.ssalgorithm.user.model.dao;

import com.ssafy.ssalgorithm.user.model.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface UserMapper {
    public UserDto findByUserId(UserDto userDto) throws SQLException;

}
