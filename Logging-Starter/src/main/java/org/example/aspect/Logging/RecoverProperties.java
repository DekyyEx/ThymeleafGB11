package org.example.aspect.Logging;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties("application.recover")
public class RecoverProperties {

    private boolean enabled = true;

    private List<String> noRecoverFor;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getNoRecoverFor() {
        return noRecoverFor;
    }

    public void setNoRecoverFor(List<String> noRecoverFor) {
        this.noRecoverFor = noRecoverFor;
    }
}
