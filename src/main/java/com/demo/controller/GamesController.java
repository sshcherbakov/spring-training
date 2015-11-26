package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Game;
import com.demo.model.Move;
import com.demo.service.GameService;

@RestController
@RequestMapping("/games")
public class GamesController {
	private static Logger log = LoggerFactory.getLogger(GamesController.class);
	
	@Autowired
	private GameService gameService;


	@RequestMapping(method=RequestMethod.GET)
	public Iterable<Game> listGames() {
		
		return gameService.listGames();
	}

	
	@RequestMapping(method=RequestMethod.POST)
	public Game createGame(@RequestParam("p") String playerName) {
		
		return gameService.startGame(playerName);
	}

	
	@RequestMapping(value="{gameId}", method=RequestMethod.GET)
	public Game getGame(@PathVariable("gameId") long gameId) {
		
		return gameService.getGame(gameId);
	}
	
	
	@RequestMapping(value="{gameId}", method=RequestMethod.POST)
	public Game moveUp(
			@PathVariable("gameId") long gameId, 
			@RequestParam("p") String playerName,
			@RequestParam("m") Move move ) {
		
		switch(move) {
		case UP:
			return gameService.moveUp(gameId, playerName);
		case DOWN:
			return gameService.moveDown(gameId, playerName);
		case RIGHT:
			return gameService.moveRight(gameId, playerName);
		case LEFT:
			return gameService.moveLeft(gameId, playerName);
		default:
			throw new IllegalStateException("Illegal move");
		}

	}
	
	
}