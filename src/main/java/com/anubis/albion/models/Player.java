package com.anubis.albion.models;

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
    private UUID player_id;
    @Nullable
    private String player_name;
    @Nullable
    private String player_code;
    @Column(name = "DT_UPDATEINFO", nullable = false)
    private LocalDateTime updateInfo;
    private boolean mainChar;

}
