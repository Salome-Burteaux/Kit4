package com.example.apiplateaujeu.Controller;

import java.util.UUID;

public record TypeDto(String gameName, UUID opponentId, int boardSize) {
}
