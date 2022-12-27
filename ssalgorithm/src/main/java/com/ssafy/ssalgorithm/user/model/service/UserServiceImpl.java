package com.ssafy.ssalgorithm.user.model.service;

import com.ssafy.ssalgorithm.exception.NotFoundUserException;
import com.ssafy.ssalgorithm.user.model.dao.UserMapper;
import com.ssafy.ssalgorithm.user.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto login(UserDto userDto) throws Exception{
        //login - 비밀번호 매치 후 비밀번호가 맞지 않는 경우 null 반환
        UserDto findUser = userMapper.findByUserId(userDto);
        if(findUser == null){
            throw new NotFoundUserException("");
        }

        if(passwordEncoder.matches(userDto.getPassword(), findUser.getPassword())){
            return findUser;
        }else{
            return null;
        }

    }

    @Override
    public String getRefreshToken(UserDto userDto) throws SQLException {
        return userMapper.findByUserId(userDto).getToken();
    }

}
