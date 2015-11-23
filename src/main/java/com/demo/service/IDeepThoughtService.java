package com.demo.service;

import java.util.concurrent.ExecutionException;

import com.demo.model.Antwort;

/**
 * <p>
 * Der IDeepThoughtService bietet Schnittstellen zu dem Supercomputer namens <a
 * href="http://en.wikipedia.org/wiki/List_of_minor_The_Hitchhiker%27s_Guide_to_the_Galaxy_characters#Deep_Thought"> Deep Thought </a>. Deep Thought
 * kennt u.a. die Antwort auf die Frage nach dem Leben, dem Universum und dem ganzen Rest
 * </p>
 * <p>
 * Deep Thought wurde von pan-dimensionalen, hyper-intelligenten Wesen erbaut, um die Antwort auf die ultimative Frage nach dem Leben, dem Universum
 * und dem ganzen Rest zu beantworten. Nach einer Rechenzeit von 7.5 Millionen Jahren stand die Antwort endlich fest: 42
 * </p>
 * <p>
 * <b>Datenquellen, Services und Subsysteme:</b>
 * <p>
 * Repositories:
 * </p>
 * <ul>
 * <li>{@link DeepThoughtRepository}</li>
 * </ul>
 * <p>
 * Tabellen:
 * </p>
 * <ul>
 * <li><a href="http://www-01.ibm.com/support/knowledgecenter/SSEPEK_11.0.0/com.ibm.db2z11.doc.sqlref/src/tpc/db2z_sysibmsysdummy1table.dita">
 * <code>SYSIBM.SYSDUMMY1</code></a></li>
 * </ul>
 * </p>
 *
 * @version 1.0
 *
 * @author Martin Friedrich
 */
public interface IDeepThoughtService {

	/**
	 * <p>
	 * Ermittelt die Antwort auf die Frage nach dem Leben, dem Universum und dem ganzen Rest.
	 * </p>
	 *
	 * <p>
	 * Die Antwort wird von <i>Deepthought</i> asynchron ermittelt.
	 * </p>
	 *
	 * @return die Antwort auf die Frage nach dem Leben, dem Universum und dem ganzen Rest
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	Antwort ermittleDieAntwort();
}
