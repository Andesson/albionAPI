package com.anubis.albion.repository;

import reactor.core.publisher.Mono;

public interface IAlbionRepository {
    public Mono<String> HttpClientAlbionBase(String apiBaseUri ,String name);
}
