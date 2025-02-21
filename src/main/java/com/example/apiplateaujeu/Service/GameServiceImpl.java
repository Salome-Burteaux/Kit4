package com.example.apiplateaujeu.Service;

import com.example.apiplateaujeu.Controller.GameDto;
import com.example.apiplateaujeu.Controller.MoveDto;
import com.example.apiplateaujeu.Controller.TypeDto;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGame;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    private final ArrayList<List<String>> games = new ArrayList<>();

    @Override
    public ArrayList<List<String>> createGame(TypeDto typeDto) {
        List<String> gameData = List.of(UUID.randomUUID().toString(), typeDto.gameName());
        games.add(gameData);
        return games;
    }

    @Override
    public GameDto getState() {
        return null;
    }

    @Override
    public ArrayList<List<String>> displayGameById(int gameId) {
        return games;
    }


    @Override
    public List<MoveDto> getAvailableMoves(int gameId) {
        return List.of();
    }

    @Override
    public List<GameDto> getAllEndedGames() {
        return List.of();
    }

    @Override
    public GameDto makeMove(int gameId, MoveDto moveDto) {
        return null;
    }
}
