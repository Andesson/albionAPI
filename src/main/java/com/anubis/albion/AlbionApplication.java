package com.anubis.albion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlbionApplication {

    public static void main(String[] args) {
        //System.setProperty("ALBION_URL", "https://west.albion-online-data.com/api/v2/stats");
        System.setProperty("ALBION_URL", "https://gameinfo.albiononline.com/api/gameinfo");
        System.setProperty("ALBION_SEARCH", "/search?q=");
        System.setProperty("ALBION_PLAYER", "/players/");
        SpringApplication.run(AlbionApplication.class, args);
    }

}
