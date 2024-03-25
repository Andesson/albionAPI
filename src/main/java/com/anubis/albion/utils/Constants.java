package com.anubis.albion.utils;

import lombok.Getter;

@Getter
public enum Constants {
    //region Albion_API
    ALBION_V2_URL("https://west.albion-online-data.com/api/v2/stats"),
    ALBION_URL_PLAYER("https://gameinfo.albiononline.com/api/gameinfo/search?q="),
    ALBION_ID_URL("https://gameinfo.albiononline.com/api/gameinfo/players/"),
    //endregion Albion_API

    ;
    private final String value;
    Constants(String value) {
        this.value = value;
    }
}
