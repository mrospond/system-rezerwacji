package com.example.reservationsystem.config;


import com.example.reservationsystem.dao.DmlTemplate;
import com.example.reservationsystem.dao.QueryTemplate;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "sql")
@PropertySource(value = "classpath:db/sql.yml", factory = YamlPropertySourceFactory.class)
public class SQLTemplates {
    private List<DmlTemplate> dmls;
    private List<QueryTemplate> queries;
}
