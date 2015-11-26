package com.demo.service;

import com.demo.model.Game;
import com.demo.model.Position;

public interface GameService {

	Iterable<Game> listGames();
	Game getGame(long id);

	Game startGame(String playerName);
	Position joinGame(long gameId, String playerName);
	Iterable<Position> getPositions(long gameId);

	Game moveUp(long gameId, String playerName);
	Game moveDown(long gameId, String playerName);
	Game moveRight(long gameId, String playerName);
	Game moveLeft(long gameId, String playerName);

}
