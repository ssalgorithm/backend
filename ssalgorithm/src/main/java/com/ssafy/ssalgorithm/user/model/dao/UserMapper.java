package com.ssafy.ssalgorithm.user.model.dao;

import com.ssafy.ssalgorithm.user.model.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface UserMapper {
    public UserDto findByUserId(UserDto userDto) throws SQLException;
    public void insert(UserDto userDto) throws SQLException;
}
