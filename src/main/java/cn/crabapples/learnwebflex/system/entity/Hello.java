package cn.crabapples.learnwebflex.system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@Table("sys_user")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Hello {
    private String id;
    private String name;
    private String username;

}
