package io.github.bapadua.consumingrest;

import io.github.bapadua.consumingrest.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@SpringBootApplication
public class ConsumingRestApplication {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            try {
                Object x = restTemplate.getForObject(
                        "https://quoters.apps.pcfone.io/api/random", Quote.class);

                Quote quote = restTemplate.getForObject(
                        "https://quoters.apps.pcfone.io/api/random", Quote.class);
            log.info(Objects.requireNonNull(quote).toString());
            } catch (Exception ex) {
                log.info(ex.getMessage());
            }

        };
    }
}
