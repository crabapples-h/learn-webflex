package cn.crabapples.learnwebflex.system.service.impl;

import cn.crabapples.learnwebflex.system.entity.Hello;
import cn.crabapples.learnwebflex.system.repository.HelloRepository;
import cn.crabapples.learnwebflex.system.service.HelloService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HelloServiceImpl implements HelloService {
    private final HelloRepository helloRepository;

    public HelloServiceImpl(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @Override
    public Flux<Hello> hello() {
        return helloRepository.findAll();
    }

    @Override
    public Mono<Hello> findByUsername(String username) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.storeDefaultMatching());
        Hello hello = new Hello();
        hello.setUsername(username);
        Example<Hello> example = Example.of(hello, matcher);
        return helloRepository.findOne(example);
    }
}
