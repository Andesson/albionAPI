package com.anubis.albion.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ApiService {

    private final WebClient client = WebClient.create();

    /*
    public Mono<String> findByName(String url) {
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                //chamada Async
                .flatMap(body -> Mono.fromCallable(() -> {
                    // Realize operações potencialmente bloqueantes aqui
                    return body;
                }).subscribeOn(Schedulers.boundedElastic()));
                //chamada Sync
                //.block();
    }
    */
    public Mono<String> findByName(String name) {
        String url = System.getProperty("ALBION_URL") + System.getProperty("ALBION_SEARCH") + name;
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .subscribeOn(Schedulers.boundedElastic());

    }
}
