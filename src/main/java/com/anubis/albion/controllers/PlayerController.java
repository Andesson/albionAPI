package com.anubis.albion.controllers;

import com.anubis.albion.dtos.PlayerDto;
import com.anubis.albion.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("player")
public class PlayerController {

    @Autowired
    ApiService apiService;

    @GetMapping("/{name}")
    public Mono<ResponseEntity<PlayerDto>> getPlayer(@PathVariable("name") String name) {
        return apiService.findByName(name);
    }

}
