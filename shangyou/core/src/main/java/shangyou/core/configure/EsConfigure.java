package shangyou.core.configure;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "es")
@Setter
@Getter
public class EsConfigure {

    private String hosts;
    private int timeout;
    private int maxRetry;

}
