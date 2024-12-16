package cn.crabapples.learnwebflex;

import cn.crabapples.learnwebflex.base.ResponseDTO;
import cn.crabapples.learnwebflex.system.entity.Hello;
import cn.crabapples.learnwebflex.system.service.HelloService;
import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@SpringBootTest
class LearnWebflexApplicationTests {
    @Autowired
    private HelloService service;
    @Autowired
    private DatabaseClient client;

    @Test
    void test2() throws InterruptedException {
        Flux<Map<String, Object>> all = client.sql("select * from sys_user")
                .fetch()
                .all()
                .log();
        all.subscribe(System.out::println);
        Thread.sleep(2000);

    }

    @Test
    void test1() throws InterruptedException {
        Mono<ResponseDTO<List<Hello>>> map = service.hello()
                .map(e -> {
                    e.setId("测试id:" + e.getId());
                    return e;
                })
                .log()
                .collectList()
                .map(ResponseDTO::new);
        map.subscribe();
        Thread.sleep(2000);

    }

    //    @AfterAll
    public static void after() throws InterruptedException {
        Thread.sleep(2000);
    }

}
