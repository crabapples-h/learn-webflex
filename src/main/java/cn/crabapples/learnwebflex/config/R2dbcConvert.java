package cn.crabapples.learnwebflex.config;

import cn.crabapples.learnwebflex.system.entity.Hello;
import io.r2dbc.spi.ColumnMetadata;
import io.r2dbc.spi.Row;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.MySqlDialect;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 自定义r2dbc转换器
 *
 * @author Mr.He
 * 2024-12-15 23:07
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mshe
 */
@Component
@ReadingConverter
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
        System.err.println(data);
        return data;
    }
}
