package com.example.apiplateaujeu.Controller;

import com.example.apiplateaujeu.Interfaces.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class GameCatalogController {

    @Autowired
    GameCatalog gameCatalog;

    @GetMapping("/games")
    public Collection<String> getAllGames() {
        return gameCatalog.getGameIdentifiers();
    }
}
