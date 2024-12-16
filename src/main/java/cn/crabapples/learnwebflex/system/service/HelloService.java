package cn.crabapples.learnwebflex.system.service;

import cn.crabapples.learnwebflex.system.entity.Hello;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HelloService {
    Flux<Hello> hello();

    Mono<Hello> findByUsername(String string);
}
