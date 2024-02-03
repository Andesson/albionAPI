package com.anubis.albion.utils;

import lombok.Getter;

@Getter
public enum Constants {
    //region Albion_API
    ALBION_V2_URL("https://west.albion-online-data.com/api/v2/stats"),
    ALBION_URL("https://gameinfo.albiononline.com/api/gameinfo"),
    ALBION_SEARCH("/search?q="),
    ALBION_PLAYER("players"),
    //endregion Albion_API

    PLAYER_URI("/players"),
    PLAYER_JSON_PATH("players");

    private final String value;

    Constants(String value) {
        this.value = value;
    }
}
