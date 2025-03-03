package com.example.apiplateaujeu.Service;

import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Service
public class GameCatalogImpl implements GameCatalog {

    private final TicTacToeGameFactory ticTacToeFactory;
    private final TaquinGameFactory taquinFactory;
    private final ConnectFourGameFactory connectFourFactory;


    public GameCatalogImpl(TicTacToeGameFactory ticTacToeFactory, TaquinGameFactory taquinFactory, ConnectFourGameFactory connectFourFactory) {
        this.ticTacToeFactory = ticTacToeFactory;
        this.taquinFactory = taquinFactory;
        this.connectFourFactory = connectFourFactory;
    }

    @Override
    public Collection<String> getGameIdentifiers() {
        return Arrays.asList(
                ticTacToeFactory.getGameFactoryId(),
                taquinFactory.getGameFactoryId(),
                connectFourFactory.getGameFactoryId()
        );
    }


}
