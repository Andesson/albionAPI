package com.anubis.albion.repository.impl;

import com.anubis.albion.repository.IAlbionRepository;
import com.anubis.albion.utils.Constants;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class AlbionRepository implements IAlbionRepository {
    private final WebClient client = WebClient.create();

    @Override
    public Mono<String> httpClientByName(String name) {
        String url = Constants.ALBION_URL.getValue() + Constants.ALBION_SEARCH.getValue() + name;
        System.out.println(url);
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
