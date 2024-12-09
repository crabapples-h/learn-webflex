package cn.crabapples.learnwebflex.system.controller;

import cn.crabapples.learnwebflex.base.ResponseDTO;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/first")
@RestController
public class FirstController {

    @RequestMapping("/hello")
    public Mono<ResponseDTO<Object>> hello(HttpExchange exchange, ServerHttpRequest request, ServerHttpResponse response) {
        System.err.println(exchange);
        System.err.println(request);
        System.err.println(response);
        return Mono.defer(() -> {
            return Mono.create((sink) -> {
                sink.success(new ResponseDTO<>(""));
            });
        });
    }
}
