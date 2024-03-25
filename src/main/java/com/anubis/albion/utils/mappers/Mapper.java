package com.anubis.albion.utils.mappers;

import com.anubis.albion.dtos.PlayerDto;
import com.anubis.albion.entities.Player;
import com.anubis.albion.models.PlayerModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.anubis.albion.utils.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class Mapper implements EntityMapper {
    private final ObjectMapper objectMapper;
    @Autowired
    public Mapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Override
    public <Source, Destination> Destination convert(Source source, Class<Destination> destinationClass) {
        Destination destination = objectMapper.convertValue(source, (Class<? extends Destination>) destinationClass);
        return destination;
    }
    @Override
    public PlayerModel convertPlayerDtoToPlayerModel(PlayerDto playerDto) {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setId(UUID.randomUUID());
        playerModel.setPlayer_id(playerDto.getPlayer_id());
        playerModel.setPlayer_name(playerDto.getPlayer_name());
        playerModel.setGuild_Id(playerDto.getGuild_Id());
        playerModel.setGuild_Name(playerDto.getGuild_Name());
        playerModel.setAlliance_Id(playerDto.getAlliance_Id());
        playerModel.setAlliance_Name(playerDto.getAlliance_Name());
        playerModel.setAvatar(playerDto.getAlliance_Name());
        playerModel.setAvatarRing(playerDto.getAvatarRing());
        playerModel.setKillFame(playerDto.getKillFame());
        playerModel.setDeathFame(playerDto.getDeathFame());
        playerModel.setFameRatio(playerDto.getFameRatio());
        playerModel.setTotalKills(playerDto.getTotalKills());
        playerModel.setGvgKills(playerDto.getGvgKills());
        playerModel.setGvgWon(playerDto.getGvgWon());
        return playerModel;
    }
    @Override
    public Player convertPlayerModelToPlayer(PlayerModel playerModel) {
        Player player = new Player();
        player.setPlayer_code(playerModel.getPlayer_id());
        player.setPlayer_name(playerModel.getPlayer_name());
        player.setGuild_Id(playerModel.getGuild_Id());
        player.setGuild_Name(playerModel.getGuild_Name());
        player.setAlliance_Id(playerModel.getAlliance_Id());
        player.setAlliance_Name(playerModel.getAlliance_Name());
        player.setAvatar(playerModel.getAlliance_Name());
        player.setAvatarRing(playerModel.getAvatarRing());
        player.setKillFame(playerModel.getKillFame());
        player.setDeathFame(playerModel.getDeathFame());
        player.setFameRatio(playerModel.getFameRatio());
        player.setTotalKills(playerModel.getTotalKills());
        player.setGvgKills(playerModel.getGvgKills());
        player.setGvgWon(playerModel.getGvgWon());
        return player;
    }
}
