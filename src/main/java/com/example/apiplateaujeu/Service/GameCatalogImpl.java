package com.example.apiplateaujeu.Service;

import com.example.apiplateaujeu.Interfaces.GameCatalog;
import com.example.apiplateaujeu.Interfaces.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class GameCatalogImpl implements GameCatalog {


    @Autowired
    TicTacToeGameFactory ticTacToeGameFactory;

    @Override
    public Collection<String> getGameIdentifiers() {

        String ticTacToeIdentifier = ticTacToeGameFactory.getTicTacToeIdentifier();
        return Arrays.asList(ticTacToeIdentifier, "gomoku", "puissance4");
    }
}
