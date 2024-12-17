package cn.crabapples.learnwebflex.system.controller;

import cn.crabapples.learnwebflex.base.ResponseDTO;
import cn.crabapples.learnwebflex.system.entity.Hello;
import cn.crabapples.learnwebflex.system.service.HelloService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/hello")
@RestController
public class HelloController {
    @Resource
    protected HelloService service;


    @GetMapping("/hello")
    @PreAuthorize("hasRole('admin')")
    public Mono<ResponseDTO<List<Hello>>> hello() {
        return service.hello().map(e -> {
            e.setId("测试id:" + e.getId());
            return e;
        }).collectList().map(ResponseDTO::new);
    }

    @RequestMapping("/world")
//    @PreAuthorize("hasPermission('world')")
    @PreAuthorize("@crabapples.hasPermission('system:user:remove')")
    public Mono<String> world() {
        return Mono.create(sink -> sink.success("world!"));
    }

    @RequestMapping("/ping")
    @PreAuthorize("hasAuthority('ping')")
    public Mono<String> ping() {
        return Mono.create(sink -> sink.success("Pong!"));
    }


}
