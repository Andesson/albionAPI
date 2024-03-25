package com.anubis.albion.utils;

import com.anubis.albion.dtos.PlayerDto;
import com.anubis.albion.entities.Player;
import com.anubis.albion.models.PlayerModel;

public interface EntityMapper {
    public PlayerModel convertPlayerDtoToPlayerModel(PlayerDto playerDto);
    public Player convertPlayerModelToPlayer(PlayerModel playerModel);
    <Source, Destination> Destination convert(Source source, Class<Destination> destinationClass);
}
