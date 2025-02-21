package com.example.apiplateaujeu.Dao;

import org.springframework.stereotype.Service;

@Service
public class TicTacToeGameFactoryImpl implements TicTacToeGameFactory {

    @Override
    public String getTicTacToeIdentifier() {
        return "TicTacToe";
    }

}
