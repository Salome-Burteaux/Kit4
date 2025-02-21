package com.example.apiplateaujeu.Service;


import com.example.apiplateaujeu.Controller.GameDto;
import com.example.apiplateaujeu.Controller.MoveDto;
import com.example.apiplateaujeu.Controller.TypeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface GameService {


    ArrayList<List<String>> createGame(TypeDto typeDto);
    GameDto getState();
    ArrayList<List<String>> displayGameById(int gameId);
    List<MoveDto> getAvailableMoves(int gameId);
    List<GameDto> getAllEndedGames();
    GameDto makeMove(int gameId, MoveDto moveDto);
}
