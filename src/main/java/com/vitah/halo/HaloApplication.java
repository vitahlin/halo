package com.vitah.halo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author vitah
 */
@SpringBootApplication
@EnableJpaAuditing
public class HaloApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaloApplication.class, args);
    }
}
