package com.example.apiplateaujeu.Service;

import com.example.apiplateaujeu.Controller.GameDto;
import com.example.apiplateaujeu.Controller.MoveDto;
import com.example.apiplateaujeu.Controller.TypeDto;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGame;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGame;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGame;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    private final ArrayList<List<String>> games = new ArrayList<>();

    @Override
    public ArrayList<List<String>> createGame(String userId, TypeDto typeDto, @Min(3) @Max(5) int boardSize) {

        // récupérer le nom du jeu et instancier le bon service
        switch(typeDto.gameName()){

            case "tictactoe":
                TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();


                ticTacToeGameFactory.createGame(boardSize, playerIds);
                break;

            case "15 puzzle":
                TaquinGameFactory taquinGameFactory = new TaquinGameFactory();
                TaquinGame taquinGame = taquinGameFactory.createGame(boardSize, playerIds);
                break;

            case "connect4":
                ConnectFourGameFactory connectFourGameFactory = new ConnectFourGameFactory();
                ConnectFourGame connectFourGame = new ConnectFourGame(boardSize, playerIds);
                break;

        }
        List<String> gameData = List.of(UUID.randomUUID().toString(), typeDto.gameName());
        games.add(gameData);
        return games;
    }

    @Override
    public GameDto getState(String userId, int gameId) {

        return null;
    }

    @Override
    public ArrayList<List<String>> displayGameById(String userId, int gameId) {
        return games;
    }


    @Override
    public List<MoveDto> getAvailableMoves(String userId, int gameId) {
        return List.of();
    }

    @Override
    public List<GameDto> getAllEndedGames(String userId) {

        return List.of();
    }

    @Override
    public GameDto makeMove(String userId, int gameId, MoveDto moveDto) {

        return null;
    }
}
