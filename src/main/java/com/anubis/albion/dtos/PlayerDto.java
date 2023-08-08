package com.anubis.albion.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class PlayerDto {

    @NotBlank
    private UUID player_id;
    @NotBlank
    private String player_name;
    @NotBlank
    private String player_code;
}
