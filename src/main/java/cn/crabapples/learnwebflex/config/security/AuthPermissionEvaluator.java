package cn.crabapples.learnwebflex.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("ss")
public class AuthPermissionEvaluator {


    public Mono<Boolean> hasPermission(Object permission) {
        Mono<SecurityContext> context = ReactiveSecurityContextHolder.getContext();
        context.log().subscribe(e -> {
            System.err.println(e);
        });

        return Mono.just(true);
    }
}
