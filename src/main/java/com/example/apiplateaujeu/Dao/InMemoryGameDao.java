package com.example.apiplateaujeu.Dao;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Repository
public class InMemoryGameDao implements GameDao {

//    @Autowired
//    GameEntityRepository gameEntityRepository;

    //stocker les parties en cours
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    @Override
    public Stream<Game> findAll() {
        return Stream.empty();
    }

    @Override
    public Optional<Game> findById(String gameId) {
        return Optional.empty();
    }

    @Override
    public Game upsert(Game game) {
        return null;
    }

    @Override
    public void delete(String gameId) {

    }
}
