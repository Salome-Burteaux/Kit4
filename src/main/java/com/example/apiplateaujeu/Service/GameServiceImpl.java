package com.example.apiplateaujeu.Service;

import com.example.apiplateaujeu.Controller.GameDto;
import com.example.apiplateaujeu.Controller.MoveDto;
import com.example.apiplateaujeu.Controller.TypeDto;
import com.example.apiplateaujeu.Controller.UserDto;
import com.example.apiplateaujeu.Dao.GameDao;
import com.example.apiplateaujeu.GamePlugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final List<GamePlugin> gamePlugins;
    private final ArrayList<Game> games = new ArrayList<>();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GameDao gameDao;


    public GameServiceImpl(List<GamePlugin> gamePlugins) {
        this.gamePlugins = gamePlugins;
    }

    @Override
    public ArrayList<Game> createGame(TypeDto typeDto) {

        // Créer la partie en fonction du type de jeu
        Game game = null;

        //récupération du plugin propre au jeu
//        GamePlugin gamePlugin = getGamePlugin(typeDto.gameName());

        for (GamePlugin plugin : gamePlugins) {
            if (plugin.getName(Locale.getDefault()).equalsIgnoreCase(typeDto.gameName())) {
                game = plugin.createGame(Optional.of(typeDto.playerCount()), Optional.ofNullable(typeDto.boardSize()));
                break;
            };
        }
        if (game == null) {
            throw new IllegalStateException("No games found");
        }


//        if (gamePlugin != null) {
//            game = gamePlugin.createGame(Optional.of(typeDto.playerCount()), Optional.of(typeDto.boardSize()));
//            games.add(game);
//        }

        gameDao.upsert(game);
        return new ArrayList<Game>((Collection) gameDao.findAll());
//        return games;
    }
    //parcours la liste de plugins pour trouver le bon gamePlugin en fonction du nom du jeu
//    private GamePlugin getGamePlugin(String gameName) {
//        return gamePlugins.stream()
//                .filter(gamePlugin -> gamePlugin.getGameName().equals(gameName))
//                .findFirst()
//                .orElse(null);
//    }


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
        String url = "http://localhost:8081/api/users/validate/" + userId.toString();

        try {
            // Effectuer la requête GET sans en-tête supplémentaire
            ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.GET, null, UserDto.class);

            // Vérifier si la réponse est correcte et que l'ID dans la réponse correspond à celui fourni
            return response.getStatusCode().is2xxSuccessful() && response.hasBody() && response.getBody().getId().equals(userId.toString());
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    @Override
    public Collection<String> getGameIdentifiers(Locale locale) {
        return gamePlugins.stream()
                .map(gamePlugins -> gamePlugins.getName(locale))
                .collect(Collectors.toList());
    }

}
