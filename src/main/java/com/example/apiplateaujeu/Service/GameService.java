package com.example.apiplateaujeu.Service;


import com.example.apiplateaujeu.Controller.GameDto;
import com.example.apiplateaujeu.Controller.MoveDto;
import com.example.apiplateaujeu.Controller.TypeDto;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public interface GameService {


    ArrayList<Game> createGame(UUID userId, TypeDto typeDto);
    GameDto displayGameById(UUID userId, UUID gameId);
    List<MoveDto> getAvailableMoves(String userId, int gameId);
    List<Game> getOngoingGamesById(UUID userId);
    GameDto makeMove(UUID userId, UUID gameId, MoveDto moveDto);
}
