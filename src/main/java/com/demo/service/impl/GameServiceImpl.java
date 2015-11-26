package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Game;
import com.demo.model.Position;
import com.demo.repository.GameRepository;
import com.demo.repository.PositionRepository;
import com.demo.service.GameService;


@Service
public class GameServiceImpl implements GameService {
	private static Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

	private static final int STEP = 1;
	
	private final GameRepository 		gameRepository;
	private final PositionRepository 	positionRepository;

	@Autowired
	public GameServiceImpl(
			final GameRepository gameRepository,
			final PositionRepository positionRepository) {
		
		this.gameRepository = gameRepository;
		this.positionRepository = positionRepository;
	}

	
	@Override
	public Iterable<Game> listGames() {
		Iterable<Game> games = gameRepository.findAll();
		return games;
	}


	@Override
	public Position joinGame(long gameId, String playerName) {
		
		Game game = gameRepository.findOne(gameId);
		if( game == null ) {
			throw new IllegalStateException("Unknonwn game");
		}
		
		Position pos = positionRepository.findFirstByGameIdAndPlayer(
				game.getId(), playerName);
		
		if( pos == null ) {
			pos = new Position();
			pos.setGame(game);
			pos.setPlayer(playerName);
			pos.setPosition("e4");
			pos = positionRepository.save(pos);
		}
		
		return pos;
	}
	
	
	@Override
	public Game getGame(long id) {
		return gameRepository.findOne(id);
	}

	@Override
	public Game startGame(String playerName) {

		Position pos = new Position();
		pos.setPlayer(playerName);
		pos.setPosition("e4");

		Game game = new Game();
		game.addPosition(pos);
		
		game = gameRepository.save(game);
		
		return game;
	}


	@Override
	public Game moveUp(long gameId, String playerName) {
		
		Position pos = positionRepository.findFirstByGameIdAndPlayer(gameId, playerName);
		if( pos == null ) {
			throw new IllegalStateException("Unknonwn game player");
		}
		
		String newPosition = advanceVertically( pos.getPosition(), STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}
	

	@Override
	public Game moveDown(long gameId, String playerName) {
		Position pos = positionRepository.findFirstByGameIdAndPlayer(gameId, playerName);
		if( pos == null ) {
			throw new IllegalStateException("Unknonwn game player");
		}
		
		String newPosition = advanceVertically( pos.getPosition(), -STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}


	@Override
	public Game moveRight(long gameId, String playerName) {
		Position pos = positionRepository.findFirstByGameIdAndPlayer(gameId, playerName);
		if( pos == null ) {
			throw new IllegalStateException("Unknonwn game player");
		}
		
		String newPosition = advanceHorizontally( pos.getPosition(), STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}


	@Override
	public Game moveLeft(long gameId, String playerName) {
		Position pos = positionRepository.findFirstByGameIdAndPlayer(gameId, playerName);
		if( pos == null ) {
			throw new IllegalStateException("Unknonwn game player");
		}
		
		String newPosition = advanceHorizontally( pos.getPosition(), -STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}

	
	String advanceVertically(String position, int delta) {
		char vpos = position.charAt(0);
		char hpos = position.charAt(1);
		vpos += delta;
		return new StringBuilder().append(vpos).append(hpos).toString();
	}

	String advanceHorizontally(String position, int delta) {
		char vpos = position.charAt(0);
		char hpos = position.charAt(1);
		hpos += delta;
		return new StringBuilder().append(vpos).append(hpos).toString();
	}


	@Override
	public Iterable<Position> getPositions(long gameId) {
		return positionRepository.findByGameId(gameId);
	}

}
