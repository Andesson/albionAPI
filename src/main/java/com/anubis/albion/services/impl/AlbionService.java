package com.anubis.albion.services.impl;

import com.anubis.albion.dtos.PlayerDto;
import com.anubis.albion.models.PlayerModel;
import com.anubis.albion.repository.IAlbionRepository;
import com.anubis.albion.services.IAlbionService;
import com.anubis.albion.utils.Constants;
import com.anubis.albion.utils.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AlbionService implements IAlbionService {
    private final IAlbionRepository albionRepository;
    private final EntityMapper Mapper;
    @Autowired
    public AlbionService(IAlbionRepository albionRepository, EntityMapper map) {
        this.albionRepository = albionRepository;
        this.Mapper = map;
    }
    @Override
    public Mono<ResponseEntity<PlayerModel>> findByName(String name) {
        if (name.isBlank()) {
            return Mono.error(new IllegalArgumentException("O nome não pode estar em branco."));
        }
        return GetFindByName(name)
                .map(playerDto -> {
                    PlayerModel playerModel = Mapper.convertPlayerDtoToPlayerModel(playerDto);
                    return ResponseEntity.ok().body(playerModel);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private Mono<PlayerDto> GetFindByName(String name) {
        return albionRepository.httpClientByName(name)
                .flatMap(playerDtoJson -> {
                    try {
                        PlayerDto playerDto = Mapper.mapJsonPath(playerDtoJson, Constants.PLAYER_JSON_PATH.getValue());
                        if (playerDto.getPlayer_name().isBlank() || playerDto.getPlayer_code().isBlank()) {
                            return Mono.empty();
                        }
                        return Mono.just(playerDto);
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                });
    }
}
