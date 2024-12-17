package cn.crabapples.learnwebflex.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("crabapples")
@Slf4j
public class AuthPermissionHandler {
    public Mono<Boolean> hasPermission(Object permission) {
        return ReactiveSecurityContextHolder.getContext()
                .log()
                .map(e -> {
                    Authentication authentication = e.getAuthentication();
                    String username = authentication.getName();
                    log.info("当前用户:[{}],权限认证:[{}]", username, permission);
                    return true;
                });
    }
}
