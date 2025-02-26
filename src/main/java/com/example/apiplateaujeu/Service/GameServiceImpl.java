package com.example.apiplateaujeu.Service;

import com.example.apiplateaujeu.AppConfig;
import com.example.apiplateaujeu.Controller.GameDto;
import com.example.apiplateaujeu.Controller.MoveDto;
import com.example.apiplateaujeu.Controller.TypeDto;
import com.example.apiplateaujeu.Controller.UserDto;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final ArrayList<Game> games = new ArrayList<>();

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ArrayList<Game> createGame(UUID userId, TypeDto typeDto) {

        if (!isValidUser(userId)) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        Set<UUID> playersIds = new HashSet<>();
        playersIds.add(userId);

        // Créer la partie en fonction du type de jeu
        Game game = null;

        switch(typeDto.gameName()){

            case "tictactoe":
                playersIds.add(typeDto.opponentId());
                TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
                game = ticTacToeGameFactory.createGame(typeDto.boardSize(), playersIds);
                break;

            case "15 puzzle":
                TaquinGameFactory taquinGameFactory = new TaquinGameFactory();
                game = taquinGameFactory.createGame(typeDto.boardSize(), playersIds);
                break;

            case "connect4":
                playersIds.add(typeDto.opponentId());
                ConnectFourGameFactory connectFourGameFactory = new ConnectFourGameFactory();
                game = connectFourGameFactory.createGame(typeDto.boardSize(), playersIds);
                break;

            default:
                throw new IllegalArgumentException("Type de jeu non supporté");
        }

        if (game != null) {
            games.add(game);
        }
        return games;
    }


    @Override
    public GameDto displayGameById(UUID userId, UUID gameId) {

        if (!isValidUser(userId)) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        Game game = games.stream()
                .filter(g -> g.getId().equals(gameId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Partie introuvable"));

        return new GameDto(game.getId().toString(), game.getCurrentPlayerId().toString(), game.getStatus().toString());
    }


    @Override
    public List<MoveDto> getAvailableMoves(String userId, int gameId) {
        return List.of();
    }

    @Override
    public List<Game> getOngoingGamesById(UUID userId) {

        if (!isValidUser(userId)) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        return games.stream()
                .filter(game -> game.getStatus().equals(GameStatus.ONGOING) && game.getPlayerIds().contains(userId))
                .collect(Collectors.toList());
    }



    @Override
    public GameDto makeMove(UUID userId, UUID gameId, MoveDto moveDto) {

        if (!isValidUser(userId)) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        //convertion de la liste games en flux (stream) pour permettre filtrage et recherche
        Game game = games.stream()
                .filter(g -> g.getId().equals(gameId))
                //récupère la 1ere partie
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Partie introuvable"));

        //vérifie si le joueur userId est présent dans getPlayerIds()
        if (!game.getPlayerIds().contains(userId)) {
            throw new IllegalArgumentException("Le joueur ne participe pas à cette partie");
        }

        //récupère l'identifiant du joueur courant (celui dont c'est le tour de jouer) et le compare à userId
        if (!game.getCurrentPlayerId().equals(userId)) {
            throw new IllegalArgumentException("Ce n'est pas votre tour de jouer");
        }

        return new GameDto(game.getId().toString(), game.getCurrentPlayerId().toString(), game.getStatus().toString());
    }

    private boolean isValidUser(UUID userId) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-UserId", userId.toString());
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        String url;
//
//        ResponseEntity<String> response = restTemplate.exchange(
//            url = "http://localhost:8081/api/users/validate/"+userId.toString(),
//            HttpMethod.GET,
//            entity,
//            String.class);

//        return response.getStatusCode().is2xxSuccessful();
        String url = "http://localhost:8081/api/users/validate/" + userId.toString();
        try {
            UserDto response = restTemplate.getForObject(url, UserDto.class);
            return response != null && response.getId().equals(userId);

        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

}
