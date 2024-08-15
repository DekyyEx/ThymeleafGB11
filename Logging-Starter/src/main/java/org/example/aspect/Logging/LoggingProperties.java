package org.example.aspect.Logging;

import lombok.Data;

import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application.logging")
public class LoggingProperties {

    private Level loggingLevel = Level.DEBUG;

    private boolean printArgs = false;

    private Output  output;

    @Data
    public static class Output {
        private String type;

        private Level level;

        private boolean enabled = true;
    }
}
