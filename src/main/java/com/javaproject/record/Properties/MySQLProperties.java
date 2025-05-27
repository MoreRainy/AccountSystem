package com.javaproject.record.Properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class MySQLProperties {
    private String url;
    private String username;
    private String password;
}
