package cn.crabapples.learnwebflex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.MySqlDialect;

//@EnableR2dbcRepositories
//@Configuration
// 似乎可有可无?
public class R2dbcConvertConfigure {

    /**
     * 配置自定义转换器后如果查询到的数据类型和自定义转换器的数据类型一致时会使用自定义转换器进行处理
     *
     * @return
     */
    @Bean
    public R2dbcCustomConversions customConversions() {
        return R2dbcCustomConversions.of(MySqlDialect.INSTANCE, new R2dbcConvert());
    }
}
