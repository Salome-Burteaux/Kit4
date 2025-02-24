package com.example.apiplateaujeu.Controller;


import com.example.apiplateaujeu.Service.GameService;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    //créer une partie
    @PostMapping("/game")
    public ResponseEntity<ArrayList<List<String>>> createGame(@RequestHeader("X-UserId") String userId, @RequestBody TypeDto typeDto) {
        ArrayList<List<String>> game = gameService.createGame(userId, typeDto);
        return ResponseEntity.ok(game); //Réponse avec statut 200 OK et le corps contenant l'objet game
    }

    //affiche une partie
    @GetMapping("/game/{gameId}")
    public ResponseEntity<ArrayList<List<String>>> displayGame(@RequestHeader("X-UserId") String userId, @PathVariable int gameId) {
        ArrayList<List<String>> game = gameService.displayGameById(userId, gameId);
        return ResponseEntity.ok(game);
    }

    //jouer un coup
    @PostMapping("/game/{gameId}/moves")
    public ResponseEntity<GameDto> move(@RequestHeader("X-UserId") String userId, @PathVariable int gameId, @RequestBody MoveDto moveDto) {
        GameDto game = gameService.makeMove(userId, gameId, moveDto);
        return ResponseEntity.ok(game);
    }

    //afficher l'état de la partie
    @GetMapping("/game/{gameId}/state")
        public ResponseEntity<GameDto> gameState(@RequestHeader("X-UserId") String userId, @PathVariable int gameId) {
        GameDto game = gameService.getState(userId, gameId);
            return ResponseEntity.ok(game);
    }

    //afficher les coups possibles
    @GetMapping("/game/{gameId}/moves")
        public ResponseEntity<List<MoveDto>> availableMoves(@RequestHeader("X-UserId") String userId, @PathVariable int gameId, @RequestParam(required = false) Boolean available) {
        List<MoveDto> moves = available != null && available ? gameService.getAvailableMoves(userId, gameId) : null;
        return ResponseEntity.ok(moves);
    }

    //afficher toutes les parties terminées
    @GetMapping("/games")
    public ResponseEntity<List<GameDto>> endedGames(@RequestHeader("X-UserId") String userId, @RequestParam(required = false) Boolean ended) {
        List<GameDto> games = ended != null && ended ? gameService.getAllEndedGames(userId) : null;
        return ResponseEntity.ok(games);
    }




}
