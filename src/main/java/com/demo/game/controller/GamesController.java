package com.demo.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.game.model.Game;
import com.demo.game.model.Move;
import com.demo.game.service.api.GameException;
import com.demo.game.service.api.GameService;

@RestController
@RequestMapping("/games")
public class GamesController {
	
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
	public Game moveJoin(
			@PathVariable("gameId") long gameId, 
			@RequestParam("p") String playerName,
			@RequestParam(name="m", required=false) Move move ) {
		
		if( move == null ) {
			return gameService.joinGame(gameId, playerName);
		}
		
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
			throw new GameException("Unknown move");
		}

	}
	
	
}