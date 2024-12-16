package cn.crabapples.learnwebflex.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * spring security 配置
 *
 * @author Ms.He
 * 2024-12-16 19:02
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mshe
 */
@Configuration
// 踩坑！之前注解打成Servlet的了，排查了两个多小的问题，最后从spring文档中发现
@EnableReactiveMethodSecurity
public class SecurityConfigure {
    //    private final AuthTokenManager tokenManager;
    private final AuthUserManager userManager;

    public SecurityConfigure(
//            AuthTokenManager tokenManager,
            AuthUserManager userManager) {
//        this.tokenManager = tokenManager;
        this.userManager = userManager;
    }

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges
                                // 放行所有GET请求 写法一
//                        .pathMatchers(HttpMethod.GET).permitAll()
                                // 放行所有静态资源 写法二
//                        .matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                // 拦截剩余请求
                                .anyExchange().authenticated()
                )
                // 开启默认http认证
                .httpBasic(withDefaults())
                // 开启默认表单登录
                .formLogin(withDefaults())
                // 禁用csrf(如果不禁用csrf会给每一个post请求加上一个安全令牌用于防止跨站请求，会导致前后端分离时无法正常请求)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authenticationManager(userManager);
        return http.build();
    }
}
