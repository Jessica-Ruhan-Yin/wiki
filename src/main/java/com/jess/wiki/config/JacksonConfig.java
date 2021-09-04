package com.jess.wiki.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
/**
 * @Description
 * 雪花算法ID，对应的后端Long类型，前端number类型，它们的精度不一样，导致精度丢失
 * 解决方法：将Long类型转成String，再传给前端
 * 方法一：统一注解，解决前后端交互Long类型精度丢失的问题
 * 方法二：在id上单独加注解
 * @JsonSerialize(using= ToStringSerializer.class)
 * private Long id;
 *
 * @Author Jessica
 * @Version v
 * @Date 2021/9/5
 */


@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}