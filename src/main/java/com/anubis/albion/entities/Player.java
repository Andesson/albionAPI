package com.anubis.albion.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "PLAYERS")
public class Player implements Serializable {

    private static final long serialVersion = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Nullable
    private String player_name;
    @Nullable
    private String player_code;
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
    private double KDA;
    @Column(name = "DT_UPDATEINFO", nullable = false)
    private LocalDateTime updateInfo;
    private boolean mainChar;

}
