package com.anubis.albion.repository.impl;

import com.anubis.albion.repository.IAlbionRepository;
import com.anubis.albion.utils.Constants;
import com.anubis.albion.utils.Exceptions;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class AlbionRepository implements IAlbionRepository {
    private final WebClient client = WebClient.create();

    @Override
    public Mono<String> HttpClientAlbionBase(String apiBaseUri,String name) {
        String url = apiBaseUri + name;
        System.out.println(url);
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getRawStatusCode() == 404) {
                        return Mono.empty();
                    } else {
                        return Mono.error(e);
                    }
                });

    }
}
