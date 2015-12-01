package com.demo.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.demo.game.model.Color;
import com.demo.game.model.Piece;

public class UtilsTests {

	@Test
	public void testGetRandom() {
		for(int i=0; i<100; i++) {
			assertNotNull( Utils.getRandom(Color.class) );
			assertNotNull( Utils.getRandom(Piece.class) );
		}
	}

	@Test
	public void testDifferentColors() {
		
		int cw=0, cb=0;
		for(int i=0; i<100; i++) {
			switch(Utils.getRandom(Color.class)){
			case white: cw++; break;
			case black: cb++; break;
			}
		}
		assertTrue(cw > 0);
		assertTrue(cb > 0);
	}
}
