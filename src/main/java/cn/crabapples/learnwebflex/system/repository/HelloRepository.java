package cn.crabapples.learnwebflex.system.repository;

import cn.crabapples.learnwebflex.system.entity.Hello;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends R2dbcRepository<Hello, String> {
}
