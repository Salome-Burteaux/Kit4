package com.example.apiplateaujeu.Dao;


import com.example.apiplateaujeu.Service.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameEntityRepository extends JpaRepository<GameEntity, Long> {


}
