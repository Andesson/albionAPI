package com.anubis.albion.utils;

import com.anubis.albion.dtos.PlayerDto;
import com.anubis.albion.models.PlayerModel;

public interface EntityMapper<T> {
    public PlayerDto mapJsonPath(String json, String path) throws Exception;
    public PlayerDto mapJson(String json) throws Exception;
    public PlayerModel convertPlayerDtoToPlayerModel(PlayerDto playerDto);
}
