package com.demo.game.service.internal;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.aop.Monitored;
import com.demo.game.model.Color;
import com.demo.game.model.Game;
import com.demo.game.model.Piece;
import com.demo.game.model.Player;
import com.demo.game.model.Position;
import com.demo.game.repository.GameRepository;
import com.demo.game.repository.PlayerRepository;
import com.demo.game.repository.PositionRepository;
import com.demo.game.service.api.GameException;
import com.demo.game.service.api.GameService;
import com.demo.utils.Utils;

@Service
@Monitored
@Transactional
@CacheConfig(cacheManager="cacheManager", cacheNames="games")
public class GameServiceImpl implements GameService {

	private static final int STEP = 1;
	
	private final GameRepository 		gameRepository;
	private final PositionRepository 	positionRepository;
	private final PlayerRepository 		playerRepository;

	
	@Autowired
	public GameServiceImpl(
			final GameRepository gameRepository,
			final PositionRepository positionRepository,
			final PlayerRepository playerRepository) {
		
		this.gameRepository = gameRepository;
		this.positionRepository = positionRepository;
		this.playerRepository = playerRepository;
	}


	@Override
	public Iterable<Game> listGames() {
		Iterable<Game> res = gameRepository.findAll();
		return res;
	}


	@Cacheable
	@Override
	public Game getGame(long gameId) {
		Game game = gameRepository.findOne(gameId);
		return game;
	}

	@Override
	public Game startGame(String playerName) {

		Player player = getPlayer(playerName);
		
		Position pos = new Position();
		pos.setPlayer(player);

		Game game = new Game();
		game.addPosition(pos);
		pos.setGame(game);

		pos.setPosition(getRandomPosition(game));

		game = gameRepository.save(game);
		
		return game;
	}


	@CachePut(key="#gameId")
	@Override
	public Game joinGame(long gameId, String playerName) {
		
		Game game = gameRepository.findOne(gameId);
		if( game == null ) {
			throw new GameException("Unknonwn game");
		}
		
		Position pos = positionRepository.findFirstByGameIdAndPlayerName(
				game.getId(), playerName);
		
		if( pos == null ) {
			pos = new Position();
			pos.setGame(game);
			pos.setPlayer(getPlayer(playerName));
			pos.setPosition(getRandomPosition(game));
			pos = positionRepository.save(pos);
			game.addPosition(pos);
		}
		
		return game;
	}

	@CacheEvict(key="#gameId")
	@Override
	public Game makeMove(long gameId, String playerName, String newPos) {
		
		Position pos = getPosition(gameId, playerName);
		
		validatePosition(newPos);
		pos.setPosition( newPos );
		pos = positionRepository.save(pos);
		
		return pos.getGame();
	}


	@CacheEvict(key="#gameId")
	@Override
	public Game moveUp(long gameId, String playerName) {
		
		Position pos = getPosition(gameId, playerName);
		
		String newPosition = advanceVertically( pos.getPosition(), STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}
	

	@CacheEvict(key="#gameId")
	@Override
	public Game moveDown(long gameId, String playerName) {
		Position pos = getPosition(gameId, playerName);
		
		String newPosition = advanceVertically( pos.getPosition(), -STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}


	@CacheEvict(key="#gameId")
	@Override
	public Game moveRight(long gameId, String playerName) {
		Position pos = getPosition(gameId, playerName);
		
		String newPosition = advanceHorizontally( pos.getPosition(), STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}


	@CacheEvict(key="#gameId")
	@Override
	public Game moveLeft(long gameId, String playerName) {
		Position pos = getPosition(gameId, playerName);
		
		String newPosition = advanceHorizontally( pos.getPosition(), -STEP );
		pos.setPosition( newPosition );
		positionRepository.save(pos);
		
		return pos.getGame();
	}

	@Override
	public void deleteGame(long gameId) {
		gameRepository.delete(gameId);
	}

	
	@ExceptionHandler()
	public void handleException(Exception ex, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), getStackString(ex));
	}

	
	private Player getPlayer(String playerName) {
		Player player = playerRepository.findFirstByName(playerName);
		if( player == null ) {
			player = new Player();
			player.setName(playerName);
			player.setColor(Utils.getRandom(Color.class));
			player.setPiece(Utils.getRandom(Piece.class));

			player = playerRepository.save(player);
		}
		return player;
	}

	private Position getPosition(long gameId, String playerName) {
		Position pos = positionRepository.findFirstByGameIdAndPlayerName(gameId, playerName);
		if( pos == null ) {
			throw new GameException("Unknonwn game player");
		}
		return pos;
	}

	
	private String advanceVertically(String position, int delta) {
		char hpos = position.charAt(0);
		char vpos = position.charAt(1);
		vpos += delta;
		String res = getPosString(hpos, vpos);
		validatePosition(res);
		return res;
	}

	private String advanceHorizontally(String position, int delta) {
		char hpos = position.charAt(0);
		char vpos = position.charAt(1);
		hpos += delta;
		String res = getPosString(hpos, vpos);
		validatePosition(res);
		return res;
	}

	
	private String getRandomPosition(Game game) {
		Random r = new Random();
		
		String res;
		int cnt = 0;
		do {
			if(cnt++ > 64) {
				throw new RuntimeException("Board is full?");
			}
			char hpos = (char) ('A' + r.nextInt(8));
			char vpos = (char) ('1' + r.nextInt(8));
			res = getPosString(hpos, vpos);
		}
		while( exists(res, game) );
		
		return res;
	}

	private boolean exists(String res, Game game) {
		if( res == null ) {
			return false;
		}
		for(Position pos : game.getPositions()) {
			if(pos.getPosition() != null &&
				pos.getPosition().toUpperCase().equals(res.toUpperCase())) {
				return true;
			}
		}
		return false;
	}


	private String getPosString(char hpos, char vpos) {
		return new StringBuilder().append(hpos).append(vpos).toString();
	}

	private void validatePosition(String newPos) {
		Assert.notNull(newPos);
		Assert.isTrue(newPos.length() == 2);
		Assert.isTrue(newPos.charAt(0) >= 'A');
		Assert.isTrue(newPos.charAt(0) <= 'H');
		Assert.isTrue(newPos.charAt(1) >= '1');
		Assert.isTrue(newPos.charAt(1) <= '8');
	}


	private String getStackString(Exception e) {
		StringBuilder s = new StringBuilder(e.getClass().getCanonicalName()).append(e.getMessage());
		for (StackTraceElement ste : e.getStackTrace()) {
			s.append("  at ").append(ste.toString()).append("\n");
		}
		return s.toString();
	}
	    
}
