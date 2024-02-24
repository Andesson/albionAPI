package com.anubis.albion.models;

import lombok.Data;

import java.util.UUID;

@Data
public class PlayerModel {

    private UUID id;
    private String player_id;
    private String player_name;
    private String guild_Id;
    private String guild_Name;
    private String alliance_Id;
    private String alliance_Name;
    private String avatar;
    private String avatarRing;
    private int killFame;
    private int deathFame;
    private double fameRatio;
    private String totalKills;
    private String gvgKills;
    private String gvgWon;
}
