package com.demo.game.service.internal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.aop.Monitored;
import com.demo.game.model.Game;
import com.demo.game.model.Position;
import com.demo.game.repository.GameRepository;
import com.demo.game.repository.PositionRepository;
import com.demo.game.service.api.GameException;
import com.demo.game.service.api.GameService;

@Service
@Monitored
@Transactional
@CacheConfig(cacheManager="cacheManager", cacheNames="games")
public class GameServiceImpl implements GameService {

	private static final String DEFAULT_POSITION = "e4";
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
		
		return gameRepository.findAll();
	}


	@CachePut(key="#gameId")
	@Override
	public Game joinGame(long gameId, String playerName) {
		
		Game game = gameRepository.findOne(gameId);
		if( game == null ) {
			throw new GameException("Unknonwn game");
		}
		
		Position pos = positionRepository.findFirstByGameIdAndPlayer(
				game.getId(), playerName);
		
		if( pos == null ) {
			pos = new Position();
			pos.setGame(game);
			pos.setPlayer(playerName);
			pos.setPosition(DEFAULT_POSITION);
			pos = positionRepository.save(pos);
		}
		
		return game;
	}
	
	
	@Cacheable
	@Override
	public Game getGame(long gameId) {
		return gameRepository.findOne(gameId);
	}

	@Override
	public Game startGame(String playerName) {

		Position pos = new Position();
		pos.setPlayer(playerName);
		pos.setPosition(DEFAULT_POSITION);

		Game game = new Game();
		game.addPosition(pos);
		pos.setGame(game);
		
		game = gameRepository.save(game);
		
		return game;
	}


	@CacheEvict(key="#gameId")
	@Override
	public Game moveUp(long gameId, String playerName) {
		
		Position pos = positionRepository.findFirstByGameIdAndPlayer(gameId, playerName);
		if( pos == null ) {
			throw new GameException("Unknonwn game player");
		}
		
		String newPosition = advanceVertically( pos.getPosition(), STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}
	

	@CacheEvict(key="#gameId")
	@Override
	public Game moveDown(long gameId, String playerName) {
		Position pos = positionRepository.findFirstByGameIdAndPlayer(gameId, playerName);
		if( pos == null ) {
			throw new GameException("Unknonwn game player");
		}
		
		String newPosition = advanceVertically( pos.getPosition(), -STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}


	@CacheEvict(key="#gameId")
	@Override
	public Game moveRight(long gameId, String playerName) {
		Position pos = positionRepository.findFirstByGameIdAndPlayer(gameId, playerName);
		if( pos == null ) {
			throw new GameException("Unknonwn game player");
		}
		
		String newPosition = advanceHorizontally( pos.getPosition(), STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}


	@CacheEvict(key="#gameId")
	@Override
	public Game moveLeft(long gameId, String playerName) {
		Position pos = positionRepository.findFirstByGameIdAndPlayer(gameId, playerName);
		if( pos == null ) {
			throw new GameException("Unknonwn game player");
		}
		
		String newPosition = advanceHorizontally( pos.getPosition(), -STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}

	
	String advanceVertically(String position, int delta) {
		char hpos = position.charAt(0);
		char vpos = position.charAt(1);
		vpos += delta;
		return new StringBuilder().append(hpos).append(vpos).toString();
	}

	String advanceHorizontally(String position, int delta) {
		char hpos = position.charAt(0);
		char vpos = position.charAt(1);
		hpos += delta;
		return new StringBuilder().append(hpos).append(vpos).toString();
	}


	@Override
	public void deleteGame(long gameId) {
		gameRepository.delete(gameId);
	}

}
