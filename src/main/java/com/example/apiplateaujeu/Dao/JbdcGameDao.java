//package com.example.apiplateaujeu.Dao;
//
//import fr.le_campus_numerique.square_games.engine.Game;
//import jakarta.validation.constraints.NotNull;
//import org.hibernate.type.descriptor.java.UUIDJavaType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//import java.util.stream.Stream;
//
//@Repository
//@Primary
//public class JbdcGameDao implements GameDao {
//
//    @Autowired
//    public NamedParameterJdbcTemplate jdbcTemplate;
//
//    @Override
//    public Stream<Game> findAll() {
//        String sql = "SELECT * FROM games";
//        List<Game> games = jdbcTemplate.query(sql, new GameRowMapper());
//        return games.stream();
//    }
//
//    @Override
//    public Optional<Game> findById(String gameId) {
//        String sql = "SELECT * FROM games WHERE id = :id";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", UUID.fromString(gameId));
//        Game game = jdbcTemplate.queryForObject(sql, params, new GameRowMapper());
//        return Optional.ofNullable(game);
//    }
//
//    @Override
//    public Game upsert(Game game) {
//        String sql = "INSERT INTO games (id, status, factory_id, player_ids, board_size) VALUES (:id, :status, :factory_id, :player_ids, :board_size) " +
//                "ON CONFLICT (id) DO UPDATE SET id = :id, status = :status, facotry_id = :factory_id, player_ids = :player_ids, board_size = :board_size";
//        Map<String, String> params = new HashMap<>();
//        params.put("id", String.valueOf(game.getId()));
//        params.put("status", game.getStatus().toString());
//        params.put("factory_id", game.getFactoryId());
//        params.put("player_ids", game.getPlayerIds().toString());
//        params.put("board_size", String.valueOf(game.getBoardSize()));
//        jdbcTemplate.update(sql, params);
//        return game;
//    }
//
//    @Override
//    public void delete(String gameId) {
//        String sql = "DELETE FROM games WHERE id = :id";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", UUID.fromString(gameId));
//        jdbcTemplate.update(sql, params);
//    }
//}
