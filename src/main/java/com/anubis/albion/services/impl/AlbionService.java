package com.anubis.albion.services.impl;

import com.anubis.albion.dtos.PlayerDto;
import com.anubis.albion.dtos.PlayerResponse;
import com.anubis.albion.models.PlayerModel;
import com.anubis.albion.repository.IAlbionRepository;
import com.anubis.albion.services.IAlbionService;
import com.anubis.albion.utils.Constants;
import com.anubis.albion.utils.EntityMapper;
import com.anubis.albion.utils.Exceptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbionService implements IAlbionService {
    private final IAlbionRepository albionRepository;
    private final ObjectMapper objectMapper;
    private final EntityMapper mapper;
    @Autowired
    public AlbionService(IAlbionRepository albionRepository, ObjectMapper objectMapper, EntityMapper map)
    {
        this.albionRepository = albionRepository;
        this.objectMapper = objectMapper;
        this.mapper = map;
    }
    //region public
    @Override
    public Mono<ResponseEntity<List<PlayerModel>>> findByName(String name)
    {
        if (name.isBlank()) {
            return Mono.error(new IllegalArgumentException(Exceptions.NAME_IS_NULL.getValue()));
        }
        return GetFindByName(name)
                .map(playerDtoList -> {
                    List<PlayerModel> playerModelList = playerDtoList.stream()
                            .map(playerDto -> mapper.convert(playerDto, PlayerModel.class))
                            .collect(Collectors.toList());
                    return ResponseEntity.ok().body(playerModelList);
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
                    PlayerModel playerModel = mapper.convert(playerDto, PlayerModel.class);
                    return ResponseEntity.ok().body(playerModel);
                });
    }
    //endregion Public
    //region Private
    private Mono<List<PlayerDto>> GetFindByName(String name)
    {
        return albionRepository.HttpClientAlbionBase(Constants.ALBION_URL_PLAYER.getValue(), name)
                .flatMap(playerDtoJson -> {
                    try {
                        PlayerResponse playerResponse = objectMapper.readValue(playerDtoJson, PlayerResponse.class);
                        List<PlayerDto> playerDtoList = Arrays.asList(playerResponse.getPlayers());
                        return Mono.just(playerDtoList);
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                });
    }
    private Mono<PlayerDto> GetFindById(String code)
    {
        return albionRepository.HttpClientAlbionBase(Constants.ALBION_ID_URL.getValue(), code)
                .flatMap(playerDtoJson -> {
                    try {
                        PlayerDto playerDto = objectMapper.readValue(playerDtoJson, PlayerDto.class);
                        return Mono.just(playerDto);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    //endregion Private
}
