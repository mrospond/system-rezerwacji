package com.example.reservationsystem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "sql")
@PropertySource(value = "classpath:db/sql.yml", factory = YamlPropertySourceFactory.class)
@Component
public class SQLConfig {
    private List<Map<String, String>> dmls;
    private List<Map<String, String>> queries;
}
