package cn.crabapples.learnwebflex.config.security;

import cn.crabapples.learnwebflex.system.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthUserManager extends AbstractUserDetailsReactiveAuthenticationManager {
    private final HelloService helloService;

    public AuthUserManager(HelloService helloService) {
        this.helloService = helloService;
    }


    @Override
    protected Mono<UserDetails> retrieveUser(String username) {
        log.info("认证用户名:[{}]", username);
        return helloService.findByUsername(username).map(e ->
                User.withUsername(username)
                        .password(e.getPassword())
                        .authorities("hello", "world", "ping")
                        .roles("ADMIN")
                        .build()).log();
    }
}
