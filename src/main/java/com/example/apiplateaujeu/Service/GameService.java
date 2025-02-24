package com.example.apiplateaujeu.Service;


import com.example.apiplateaujeu.Controller.GameDto;
import com.example.apiplateaujeu.Controller.MoveDto;
import com.example.apiplateaujeu.Controller.TypeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface GameService {


    ArrayList<List<String>> createGame(String userId, TypeDto typeDto, );
    GameDto getState(String userId, int gameId);
    ArrayList<List<String>> displayGameById(String userId, int gameId);
    List<MoveDto> getAvailableMoves(String userId, int gameId);
    List<GameDto> getAllEndedGames(String userId);
    GameDto makeMove(String userId, int gameId, MoveDto moveDto);
}
