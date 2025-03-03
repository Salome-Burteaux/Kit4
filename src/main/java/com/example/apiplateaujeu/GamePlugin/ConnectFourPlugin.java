package com.example.apiplateaujeu.GamePlugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class ConnectFourPlugin implements GamePlugin {

    @Autowired
    private MessageSource messageSource;

    @Value("${game.connectfour.default-player-counts}")
    private int defaultPlayerCount;

    @Value("${game.connectfour.default-board-size}")
    private int defaultBoardSize;


    private final ConnectFourGameFactory gameFactory;

    @Autowired
    public ConnectFourPlugin(MessageSource messageSource) {
        this.gameFactory = new ConnectFourGameFactory();
        this.messageSource = messageSource;
    }
//    @Override
//    public String getGameName() {
//
//        return "connect4";
//    }

    @Override
    public String getName(Locale locale) {

        return messageSource.getMessage("game.connectfour.name", null, locale);
    }

    @Override
    public Game createGame(Optional<Integer> playerCount, Optional<Integer> boardSize) {

        return gameFactory.createGame(playerCount.orElse(defaultPlayerCount), boardSize.orElse(defaultBoardSize));

    }
}
