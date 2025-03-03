package com.example.apiplateaujeu.Controller;


import com.example.apiplateaujeu.AppConfig;
import com.example.apiplateaujeu.Service.GameService;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;


    @Autowired
    private AppConfig appConfig;

    //créer une partie
    @PostMapping("/game")
    public ResponseEntity<ArrayList<Game>> createGame(@RequestHeader("X-UserId") UUID userId, @RequestBody TypeDto typeDto) {
        ArrayList<Game> game = gameService.createGame(typeDto);
        return ResponseEntity.ok(game); //Réponse avec statut 200 OK et le corps contenant l'objet game
    }

    //affiche une partie
    @GetMapping("/game/{gameId}")
    public ResponseEntity<GameDto> displayGame(@RequestHeader("X-UserId") UUID userId, @PathVariable UUID gameId) {
        GameDto game = gameService.displayGameById(userId, gameId);
        return ResponseEntity.ok(game);
    }

    //jouer un coup
    @PostMapping("/game/{gameId}/move")
    public ResponseEntity<GameDto> move(@RequestHeader("X-UserId") UUID userId, @PathVariable UUID gameId, @RequestBody MoveDto moveDto) {
        GameDto game = gameService.makeMove(userId, gameId, moveDto);
        return ResponseEntity.ok(game);
    }

    //afficher l'état de la partie
    @GetMapping("/game/{gameId}/state")
        public ResponseEntity<GameDto> gameState(@RequestHeader("X-UserId") UUID userId, @PathVariable int gameId) {

            return null;
    }

    //afficher les coups possibles
    @GetMapping("/game/{gameId}/moves")
        public ResponseEntity<List<MoveDto>> availableMoves(@RequestHeader("X-UserId") String userId, @PathVariable int gameId, @RequestParam(required = false) Boolean available) {
        List<MoveDto> moves = available != null && available ? gameService.getAvailableMoves(userId, gameId) : null;
        return ResponseEntity.ok(moves);
    }

    //afficher toutes les parties en cours
    @GetMapping("/games/ongoing")
    public ResponseEntity<List<Game>> ongoingGamesById(@RequestHeader("X-UserId") UUID userId) {
        List<Game> games = gameService.getOngoingGamesById(userId);
        return ResponseEntity.ok(games);
    }




}
