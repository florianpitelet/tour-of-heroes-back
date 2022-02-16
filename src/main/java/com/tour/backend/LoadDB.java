package com.tour.backend;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDB {
    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

    @Bean
    CommandLineRunner initDatabase(HeroRepo repository){

        return args -> {
            log.info("Preloading" + repository.save(new Hero("Windstorm", "Controls the weather")));
            log.info("Preloading" + repository.save(new Hero("Mr Nice","Nice with everyone")));
        };
    }
}
