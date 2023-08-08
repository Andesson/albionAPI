package com.anubis.albion.services;

import com.anubis.albion.dtos.PlayerDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;

@Service
public class ApiService {

    private final WebClient client = WebClient.create();

    public Mono<String> httpClienteName(String name) {
        String url = System.getProperty("ALBION_URL") + System.getProperty("ALBION_SEARCH") + name;
        System.out.println(url);
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .subscribeOn(Schedulers.boundedElastic());

    }

    public Mono<ResponseEntity<PlayerDto>> findByName(String name) {
        Mono<String> retornoMono = httpClienteName(name);
        return retornoMono.map(retorno -> {
            System.out.println(retorno);
            try {
                return ResponseEntity.status(HttpStatus.OK).body(jsonToPlayerDto(retorno));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String extractPlayerNameFromJson(String json, String param) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        JsonNode playerNameNode = jsonNode.findPath("players").findPath(param);
        return playerNameNode.asText();
    }

    public PlayerDto jsonToPlayerDto(String json) throws IOException {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPlayer_name(extractPlayerNameFromJson(json, "Name"));
        playerDto.setPlayer_code(extractPlayerNameFromJson(json, "Id"));
        playerDto.setKda(extractPlayerNameFromJson(json, "FameRatio"));
        return playerDto;
    }
}
