package com.wax.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private String clientId;
    private List<String> roles;
    
    
    public static UserDto defultUser(String userName){
        UserDto user = new UserDto();
        user.setId(1L);
        user.setUsername(userName);
        user.setPassword(new BCryptPasswordEncoder().encode(userName));
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        roles.add("user");
        user.setRoles(roles);
        return user;
    }
    
}