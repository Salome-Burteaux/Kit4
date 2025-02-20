package com.example.apiplateaujeu.Service;

import com.example.apiplateaujeu.Interfaces.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeGameFactoryImpl implements TicTacToeGameFactory {

    @Override
    public String getTicTacToeIdentifier() {
        return "TicTacToe";
    }

}
