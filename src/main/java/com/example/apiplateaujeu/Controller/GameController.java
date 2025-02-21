package com.example.apiplateaujeu.Controller;


import com.example.apiplateaujeu.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    //créer une partie
    @PostMapping("/game")
    public ArrayList<List<String>> createGame(@RequestBody TypeDto typeDto) {
        return gameService.createGame(typeDto);
    }

    //affiche une partie
    @GetMapping("/game/{gameId}")
    public ArrayList<List<String>> displayGame(@PathVariable int gameId) {
        return gameService.displayGameById(gameId);
    }

    //jouer un coup
    @PostMapping("/game/{gameId}/moves")
    public GameDto move(@PathVariable int gameId, @RequestBody MoveDto moveDto) {
        return gameService.makeMove(gameId, moveDto);
    }

    //afficher l'état de la partie
    @GetMapping("/game/{gameId}/state")
        public GameDto gameState(@PathVariable int gameId) {
            return gameService.getState();
    }

    //afficher les coups possibles
    @GetMapping("/game/{gameId}/moves")
        public List<MoveDto> availableMoves(@PathVariable int gameId, @RequestParam(required = false) Boolean available) {
        if (available != null && available) {
            return gameService.getAvailableMoves(gameId);
        }
        return null;
    }

    //afficher toutes les parties terminées
    @GetMapping("/games")
    public List<GameDto> endedGames(@RequestParam(required = false) Boolean ended) {
        if (ended != null && ended) {
            return gameService.getAllEndedGames();
        }
        return null;
    }




}
