package com.anubis.albion.repository;

import reactor.core.publisher.Mono;

public interface IAlbionRepository {
    Mono<String> HttpClientAlbionBase(String apiBaseUri, String name);
}
