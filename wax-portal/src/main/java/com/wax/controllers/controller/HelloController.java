package com.wax.controllers.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wax
 * @version 1.0.0
 */
@RestController
public class HelloController {
    
    
    @RequestMapping("/hello")
    private String hello(){
        return "OK";
    }
    
    
}
