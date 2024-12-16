package cn.crabapples.learnwebflex.system.service;

import cn.crabapples.learnwebflex.system.entity.Hello;
import reactor.core.publisher.Flux;

public interface HelloService {
    Flux<Hello> hello();
}
