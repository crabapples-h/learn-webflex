package cn.crabapples.learnwebflex.system.controller;

import cn.crabapples.learnwebflex.base.BaseController;
import cn.crabapples.learnwebflex.base.ResponseDTO;
import cn.crabapples.learnwebflex.system.entity.Hello;
import cn.crabapples.learnwebflex.system.service.HelloService;
import jakarta.annotation.Resource;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/hello")
@RestController
public class HelloController {
    @Resource
    protected HelloService service;


    @RequestMapping("/hello")
    public Mono<ResponseDTO<List<Hello>>> hello() {
        return service.hello().map(e -> {
            e.setId("测试id:" + e.getId());
            return e;
        }).collectList().map(ResponseDTO::new);
    }

    @RequestMapping("/ping")
    public Mono<String> ping() {
        return Mono.create(sink -> sink.success("Pong!"));
    }
}
