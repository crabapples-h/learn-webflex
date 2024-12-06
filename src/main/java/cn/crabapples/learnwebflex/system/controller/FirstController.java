package cn.crabapples.learnwebflex.system.controller;

import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/first")
@RestController
public class FirstController {

    @RequestMapping("/hello")
    public Mono<String> hello(HttpExchange exchange) {
        System.err.println(exchange);
        return Mono.defer(() -> {
            return Mono.create((sink) -> {
                sink.success("hello world");
            });
        });
    }
}
