package org.bmn.telegrampooling.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@Getter
public class BotConfig {

    // Аннотация @Value позволяет задавать значение полю путем считывания из application.yaml
    @Value("${BOT_NAME}")
    private String botUserName;

    @Value("${BOT_TOKEN}")
    private String botToken;

}
