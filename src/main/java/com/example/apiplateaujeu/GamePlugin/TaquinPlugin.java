package com.example.apiplateaujeu.GamePlugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class TaquinPlugin implements GamePlugin {

    @Autowired
    private MessageSource messageSource;

    @Value("${game.taquin.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.taquin.default-board-size}")
    private int defaultBoardSize;

    private final TaquinGameFactory gameFactory;

    @Autowired
    public TaquinPlugin(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.gameFactory = new TaquinGameFactory();
    }

//    @Override
//    public String getGameName() {
//        return "15 puzzle";
//    }

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.taquin.name", null, locale);
    }

    @Override
    public Game createGame(Optional<Integer> playerCount, Optional<Integer> boardSize) {

        return gameFactory.createGame(playerCount.orElse(defaultPlayerCount), boardSize.orElse(defaultBoardSize));

    }
}



