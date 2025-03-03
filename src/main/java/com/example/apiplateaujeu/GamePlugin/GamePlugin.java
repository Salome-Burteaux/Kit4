package com.example.apiplateaujeu.GamePlugin;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;


public interface GamePlugin
{
//    String getGameName();
    String getName(Locale locale);
    Game createGame(Optional<Integer> playerCount, Optional<Integer>boardSize);
}
