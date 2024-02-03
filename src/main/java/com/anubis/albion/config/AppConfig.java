package com.anubis.albion.config;

import com.anubis.albion.repository.impl.AlbionRepository;
import com.anubis.albion.repository.IAlbionRepository;
import com.anubis.albion.utils.EntityMapper;
import com.anubis.albion.utils.mappers.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public IAlbionRepository albionRepository() {
        return new AlbionRepository();
    }
    @Bean
    public EntityMapper<?> entityMapper(ObjectMapper objectMapper) {
        return new Mapper<>(objectMapper);
    }
}
