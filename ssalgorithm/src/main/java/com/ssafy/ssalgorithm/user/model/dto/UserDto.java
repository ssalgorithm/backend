package com.ssafy.ssalgorithm.user.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private String joinDate;
    private String token;
}
