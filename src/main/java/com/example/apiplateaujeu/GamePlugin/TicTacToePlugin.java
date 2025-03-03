package com.example.apiplateaujeu.GamePlugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Component
public class TicTacToePlugin implements GamePlugin {


    private final MessageSource messageSource;

    @Value("${game.tictactoe.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.tictactoe.default-board-size}")
    private int defaultBoardSize;


    private final TicTacToeGameFactory gameFactory;

    @Autowired
    public TicTacToePlugin(MessageSource messageSource) {
        this.gameFactory = new TicTacToeGameFactory();
        this.messageSource = messageSource;
    }

//    public String getGameName() {
//        return "tictactoe";
//    }

    //insertion de messageSource qui appelle la variable game.tictactoe.name du fichier de langue correspondant
    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.tictactoe.name", null, locale);
    }

    @Override
    public Game createGame(Optional<Integer> playerCount, Optional<Integer> boardSize) {
        return gameFactory.createGame(playerCount.orElse(defaultPlayerCount), boardSize.orElse(defaultBoardSize));
    }
}
