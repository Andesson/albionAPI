package com.anubis.albion.services;

import com.anubis.albion.models.PlayerModel;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IAlbionService {
    Mono<ResponseEntity<List<PlayerModel>>> findByName(String name);
    Mono<ResponseEntity<PlayerModel>> findByCode(String player_id);
}
