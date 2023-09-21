package com.merchant.trading.trading.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "tradingapp.properties")
@Setter
@Getter
public class ConfigProperties {

    public Map<Integer, List<String>> signal;
}