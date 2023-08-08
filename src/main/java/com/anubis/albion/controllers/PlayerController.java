package com.anubis.albion.controllers;

import com.anubis.albion.services.ApiService;
import com.anubis.albion.services.PlayerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("player")
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @Autowired
    ApiService apiService;

    @GetMapping("/{name}")
    public Mono<ResponseEntity<String>> getPlayer(@PathVariable("name") String name) throws IOException, InterruptedException {
        Mono<String> retornoMono = apiService.findByName(name);
        return retornoMono.map(retorno -> {
            String playerName = null; // Extrair o campo "Name" do JSON
            try {
                playerName = extractPlayerNameFromJson(retorno);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(playerName);
            return ResponseEntity.status(HttpStatus.OK).body(playerName);
        });
    }

    private String extractPlayerNameFromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        JsonNode playerNameNode = jsonNode.findPath("Name");
        String playerName = playerNameNode.asText();

        return playerName;
    }
}
