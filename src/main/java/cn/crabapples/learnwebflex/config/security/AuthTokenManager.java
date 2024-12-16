//package cn.crabapples.learnwebflex.config.security;
//
//import cn.crabapples.learnwebflex.system.service.HelloService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.util.ArrayList;
//
//@Slf4j
//@Component
//public class AuthTokenManager implements ReactiveAuthenticationManager {
//    private final HelloService helloService;
//
//    public AuthTokenManager(HelloService helloService) {
//        this.helloService = helloService;
//    }
//
//    @Override
//    public Mono<Authentication> authenticate(Authentication authentication) {
//        Object username = authentication.getPrincipal();
//        Object password = authentication.getCredentials();
//        return helloService.findByUsername(username.toString()).map(e -> {
//            if (password.equals(e.getPassword())) {
//                ArrayList<GrantedAuthority> authList = new ArrayList<>();
//                authList.add(new SimpleGrantedAuthority("hello"));
//                authList.add(new SimpleGrantedAuthority("world"));
//                authList.add(new SimpleGrantedAuthority("ping"));
//                log.info("认证成功用户名:{} 密码:{}", username, password);
//                UserDetails user =  User.builder()
//                           .username(e.getUsername())
//                           .password(e.getPassword())
//                           .roles("hello")
//                           .roles("world")
//                           .roles("ping")
//                           .build();
//                return UsernamePasswordAuthenticationToken.authenticated(username, password, authList);
//            }
//            log.info(",认证失败用户名:{}", username);
//            return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
//        });
//
//    }
//}
