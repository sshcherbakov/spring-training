package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

}
