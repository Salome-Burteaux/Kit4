package com.example.apiplateaujeu.Service;


import com.example.apiplateaujeu.Controller.GameDto;
import com.example.apiplateaujeu.Controller.MoveDto;
import com.example.apiplateaujeu.Controller.TypeDto;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.*;


public interface GameService {



    GameDto displayGameById(UUID userId, UUID gameId);
    List<MoveDto> getAvailableMoves(String userId, int gameId);
    List<Game> getOngoingGamesById(UUID userId);
    GameDto makeMove(UUID userId, UUID gameId, MoveDto moveDto);
    ArrayList<Game> createGame(TypeDto typeDto);
    Collection<String> getGameIdentifiers(Locale locale);
}
