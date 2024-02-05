package com.anubis.albion.services.impl;

import com.anubis.albion.dtos.PlayerDto;
import com.anubis.albion.models.PlayerModel;
import com.anubis.albion.repository.IAlbionRepository;
import com.anubis.albion.services.IAlbionService;
import com.anubis.albion.utils.Constants;
import com.anubis.albion.utils.EntityMapper;
import com.anubis.albion.utils.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AlbionService implements IAlbionService {
    private final IAlbionRepository albionRepository;
    private final EntityMapper Mapper;
    @Autowired
    public AlbionService(IAlbionRepository albionRepository, EntityMapper map)
    {
        this.albionRepository = albionRepository;
        this.Mapper = map;
    }
    //region public
    @Override
    public Mono<ResponseEntity<PlayerModel>> findByName(String name)
    {
        if (name.isBlank()) {
            return Mono.error(new IllegalArgumentException(Exceptions.NAME_IS_NULL.getValue()));
        }
        return GetFindByName(name)
                .map(playerDto -> {
                    PlayerModel playerModel = Mapper.convertPlayerDtoToPlayerModel(playerDto);
                    return ResponseEntity.ok().body(playerModel);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @Override
    public Mono<ResponseEntity<PlayerModel>> findByCode(String player_code)
    {
        if (player_code.isBlank()) {
            return Mono.error(new IllegalArgumentException(Exceptions.NAME_IS_NULL.getValue()));
        }
        return GetFindById(player_code)
                .map(playerDto -> {
                    PlayerModel playerModel = Mapper.convertPlayerDtoToPlayerModel(playerDto);
                    return ResponseEntity.ok().body(playerModel);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    //endregion Public
    //region Private
    private Mono<PlayerDto> GetFindByName(String name)
    {
        return albionRepository.HttpClientAlbionBase(Constants.ALBION_URL_PLAYER.getValue(), name)
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

    private Mono<PlayerDto> GetFindById(String id)
    {
        return albionRepository.HttpClientAlbionBase(Constants.ALBION_ID_URL.getValue(), id)
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
    //endregion Private
}
