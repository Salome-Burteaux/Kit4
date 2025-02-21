package com.example.apiplateaujeu.Controller;

import com.example.apiplateaujeu.Service.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;

    //lister les jeux
    @GetMapping("/catalog")
    public Collection<String> getAllGames() {
        return gameCatalog.getGameIdentifiers();
    }
}
