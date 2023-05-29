package com.wax.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

@SpringBootTest
class GatewayApplicationTests {

    @Test
    void contextLoads() {
        PathMatcher pathMatcher = new AntPathMatcher();
        System.out.println(pathMatcher.match("/wax-server/oauth/**", "/oauth/token"));
    }

}
