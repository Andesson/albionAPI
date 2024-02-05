package com.anubis.albion.utils.mappers;

import com.anubis.albion.dtos.PlayerDto;
import com.anubis.albion.models.PlayerModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.anubis.albion.utils.EntityMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class Mapper<T> implements EntityMapper<T> {
    private final ObjectMapper objectMapper;
    @Autowired
    public Mapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public PlayerDto mapJsonPath(String json, String path) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            PlayerDto playerDto = new PlayerDto();
            playerDto.setPlayer_code(extractFromJson(jsonNode, "Id", path));
            playerDto.setPlayer_name(extractFromJson(jsonNode, "Name", path));
            playerDto.setKda(extractFromJson(jsonNode, "FameRatio", path));
            playerDto.setKillFame(extractFromJson(jsonNode, "KillFame", path));
            playerDto.setDeathFame(extractFromJson(jsonNode, "DeathFame", path));

            return playerDto;
        } catch (IOException e) {
            throw new RuntimeException("Error mapping JSON to PlayerDto:", e);
        }
    }

    @Override
    public PlayerDto mapJson(String json) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            PlayerDto playerDto = new PlayerDto();
            playerDto.setPlayer_code(extractFromJson(jsonNode, "Id", null));
            playerDto.setPlayer_name(extractFromJson(jsonNode, "Name", null));
            playerDto.setKda(extractFromJson(jsonNode, "FameRatio", null));
            playerDto.setKillFame(extractFromJson(jsonNode, "KillFame", null));
            playerDto.setDeathFame(extractFromJson(jsonNode, "DeathFame", null));

            return playerDto;
        } catch (IOException e) {
            throw new RuntimeException("Error mapping JSON to PlayerDto:", e);
        }
    }

    private String extractFromJson(JsonNode jsonNode, String param, String path) {
        if (path.isEmpty()){
            JsonNode playerNameNode = jsonNode.findPath(path).findPath(param);
            return playerNameNode.asText();
        } else {
            JsonNode playerNameNode = jsonNode.findPath(param);
            return playerNameNode.asText();
        }
    }

    public PlayerModel convertPlayerDtoToPlayerModel(PlayerDto playerDto) {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setPlayer_id(UUID.randomUUID());
        playerModel.setPlayer_name(playerDto.getPlayer_name());
        playerModel.setPlayer_code(playerDto.getPlayer_code());
        playerModel.setKDA((double) playerModel.getKDA());
        return playerModel;
    }
}
