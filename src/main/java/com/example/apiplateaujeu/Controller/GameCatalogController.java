package com.example.apiplateaujeu.Controller;

import com.example.apiplateaujeu.Service.GameCatalog;
import com.example.apiplateaujeu.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Locale;

@RestController
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;

    @Autowired
    private GameService gameService;

    //lister les jeux
    @GetMapping("/catalog")
    public Collection<String> getAllGames() {
        return gameCatalog.getGameIdentifiers();
    }

    @GetMapping("/identifiers")
    public Collection<String> getAllIdentifiers(@RequestHeader("Accept-Language") Locale locale) {
        return gameService.getGameIdentifiers(locale);
    }


}
