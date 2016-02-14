/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI.MoveDetector;

import fi.henri.chessai.game.logic.LogicHandler;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class MoveObserverTest {

    private LogicHandler handler;
    private MoveObserver observer;

    @Before
    public void setUp() {
        this.handler = new LogicHandler();
        this.observer = new MoveObserver(handler);
    }

    @Test
    public void startingMovesWithWhite() {
        assertEquals(20, observer.getPossibleMoves().size());
    }

    @Test
    public void secondTurn() {
        assertTrue(handler.movePiece(52, 36));
        assertTrue(handler.movePiece(11, 27));
        assertEquals(31, observer.getPossibleMoves().size());
    }
}
