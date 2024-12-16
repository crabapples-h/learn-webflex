package cn.crabapples.learnwebflex.system.service.impl;

import cn.crabapples.learnwebflex.system.entity.Hello;
import cn.crabapples.learnwebflex.system.repository.HelloRepository;
import cn.crabapples.learnwebflex.system.service.HelloService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
}
