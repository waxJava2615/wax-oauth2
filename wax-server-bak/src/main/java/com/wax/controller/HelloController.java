package com.wax.controller;

import com.wax.entity.AdminUser;
import com.wax.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wax
 * @version 1.0.0
 * @date 2023/4/25
 * @description 测试
 */
@RestController
public class HelloController {
    
    @Autowired
    AdminUserService adminUserService;
    
    @RequestMapping({"/hello", "/hello2"})
    public String hello() {
        if (true) {
            throw new RuntimeException("123");
        }
        AdminUser adminUser = adminUserService.getById(1);
        return "hello " + adminUser.getUserName();
    }
    
    
}
