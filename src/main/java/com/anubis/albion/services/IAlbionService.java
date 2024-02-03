package com.anubis.albion.services;

import com.anubis.albion.models.PlayerModel;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface IAlbionService {
    public Mono<ResponseEntity<PlayerModel>> findByName(String name);
}
