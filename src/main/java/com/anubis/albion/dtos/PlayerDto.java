package com.anubis.albion.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlayerDto {

    @NotBlank
    private String player_name;
    @NotBlank
    private String player_code;
    @NotBlank
    private String kda;
}
