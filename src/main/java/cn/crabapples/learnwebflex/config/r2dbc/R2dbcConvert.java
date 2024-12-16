package cn.crabapples.learnwebflex.config.r2dbc;

import cn.crabapples.learnwebflex.system.entity.Hello;
import io.r2dbc.spi.ColumnMetadata;
import io.r2dbc.spi.Row;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.MySqlDialect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 自定义r2dbc转换器
 * <p>
 * ReadingConverter 从数据库读取时使用
 * WritingConverter 写入数据库时使用
 * 如果不加注解则读取和写入都适用该转换器
 *
 * @author Mr.He
 * 2024-12-15 23:07
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mshe
 */
//@EnableR2dbcRepositories
@Configuration
@ReadingConverter // 从数据库读取时使用
//@WritingConverter // 写入数据库时使用
public class R2dbcConvert implements Converter<Row, Hello> {

    @Override
    public Hello convert(Row source) {
        Hello data = new Hello();
        List<? extends ColumnMetadata> columnMetadatas = source.getMetadata().getColumnMetadatas();
        for (ColumnMetadata metadata : columnMetadatas) {
            String name = metadata.getName();
            try {
                Field field = Hello.class.getDeclaredField(name);
                field.setAccessible(true);
                field.set(data, source.get(name));
            } catch (IllegalAccessException | NoSuchFieldException ignored) {
            }
        }
        return data;
    }

    /**
     * 配置自定义转换器后如果查询到的数据类型和自定义转换器的数据类型一致时会使用自定义转换器进行处理
     */
    @Bean
    public R2dbcCustomConversions customConversions() {
        return R2dbcCustomConversions.of(MySqlDialect.INSTANCE, new R2dbcConvert());
    }
}
