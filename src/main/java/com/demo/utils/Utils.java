package com.demo.utils;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Random;

public final class Utils {
	
	private Utils() {}

	private static transient Random random = new Random();

	public static <E extends Enum<E>> E getRandom(Class<E> clazz) {
		EnumSet<E> es = EnumSet.allOf(clazz);
		int ind = random.nextInt(es.size());
		int i=0;
		Iterator<E> it = es.iterator();
		E res = null;
		while(it.hasNext() && i++<=ind) {
			res = it.next();
		}
		return res;
	}

}
