package com.anubis.albion.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class PlayerDto {


    @JsonAlias({"player_id", "Id"})
    private String player_id;

    @JsonAlias({"player_name", "Name"})
    private String player_name;

    @JsonAlias({"guild_Id", "GuildId"})
    private String guild_Id;

    @JsonAlias({"guild_Name", "GuildName"})
    private String guild_Name;

    @JsonAlias({"alliance_Id", "AllianceId"})
    private String alliance_Id;

    @JsonAlias({"alliance_Name", "AllianceName"})
    private String alliance_Name;

    @JsonAlias({"avatar", "Avatar"})
    private String avatar;

    @JsonAlias({"avatarRing", "AvatarRing"})
    private String avatarRing;

    @JsonAlias({"killFame", "KillFame"})
    private String killFame;

    @JsonAlias({"deathFame", "DeathFame"})
    private String deathFame;

    @JsonAlias({"fameRatio", "FameRatio"})
    private String fameRatio;

    @JsonAlias({"totalKills", "totalKills"})
    private String totalKills;

    @JsonAlias({"gvgKills", "gvgKills"})
    private String gvgKills;

    @JsonAlias({"gvgWon", "gvgWon"})
    private String gvgWon;
}
