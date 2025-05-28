package aks.com.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

/**
 * Jackson配置类，用于自定义序列化行为
 * 特别是将Long类型转为String，避免前端JavaScript精度丢失问题
 * @author 34011
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 创建一个自定义模块，只将Long类型转为String
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, new JsonSerializer<Long>() {
            @Override
            public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(value.toString());
            }
        });

        // 同样处理long基本类型
        simpleModule.addSerializer(long.class, new JsonSerializer<Long>() {
            @Override
            public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(value.toString());
            }
        });

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
