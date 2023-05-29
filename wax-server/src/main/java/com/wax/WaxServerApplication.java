package com.wax;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootApplication
public class WaxServerApplication {
    
    public static void main(String[] args) {
        log.info("admin-app ==> {}", new BCryptPasswordEncoder().encode("admin-app"));
        log.info("portal-app ==> {}", new BCryptPasswordEncoder().encode("portal-app"));
        log.info("admin ==> {}", new BCryptPasswordEncoder().encode("admin"));
        SpringApplication.run(WaxServerApplication.class, args);
    }
    
}
