package org.example.springmvcthymeleaf.repository;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sql")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class QueryProperties {
    String sqlFindAll;
    String sqlSave;
    String sqlDeleteById;
    String sqlUpdateUser;
    String sqlGetOne;
}
