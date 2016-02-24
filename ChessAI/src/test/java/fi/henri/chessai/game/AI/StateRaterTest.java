/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI;

import fi.henri.chessai.game.logic.LogicHandler;
import static java.awt.Color.BLACK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class StateRaterTest {

    private LogicHandler handler;
    private StateRater rater;

    @Before
    public void SetUp() {
        this.handler = new LogicHandler();
        this.rater = new StateRater(BLACK);
    }

    @Test
    public void pointsAtStartOfTheGame() {
        assertEquals(0, rater.getBoardStateValue(handler.getChessBoard().getBoard()));
    }

    @Test
    public void pointsAfterFirstPieceHasBeenEaten() {
        assertTrue(handler.movePiece(52, 36));
        assertTrue(handler.movePiece(11, 27));
        assertTrue(handler.movePiece(36, 27));
        assertEquals(-100, rater.getBoardStateValue(handler.getChessBoard().getBoard()));
    }
}
