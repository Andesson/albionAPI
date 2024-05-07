package com.anubis.albion.controllers;

import com.anubis.albion.exception.CustomException;
import com.anubis.albion.models.PlayerModel;
import com.anubis.albion.services.IAlbionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("player")
public class PlayerController {

    @Autowired
    IAlbionService _albionService;

    @GetMapping("/{name}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<List<PlayerModel>>> GetPlayerName(@PathVariable("name") String name) {
        try {
            return _albionService.findByName(name);
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage(), ex);
        }
    }

    @GetMapping("/players/{player_code}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<PlayerModel>> GetPlayerByCode(@PathVariable("player_code") String code) {
        try {
            return _albionService.findByCode(code);
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage(), ex);
        }
    }
}
