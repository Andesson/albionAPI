package com.anubis.albion.utils;

import com.anubis.albion.dtos.PlayerDto;
import com.anubis.albion.entities.Player;
import com.anubis.albion.models.PlayerModel;

public interface EntityMapper {
    PlayerModel convertPlayerDtoToPlayerModel(PlayerDto playerDto);
    Player convertPlayerModelToPlayer(PlayerModel playerModel);
    <Source, Destination> Destination convert(Source source, Class<Destination> destinationClass);
}
