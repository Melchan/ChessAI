/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 *
 * @author manhenri
 */
public class LogicHandlerTest {
    private LogicHandler handler;

    @Before
    public void setUp() {
        this.handler = new LogicHandler();
        handler.newGame();
    }
    
    @Test
    public void fastGameStartToFinnishEndingToWhiteVictory() {
        handler.movePiece(52, 36);
        handler.movePiece(13, 39);
        handler.movePiece(59, 45);
        handler.movePiece(6, 21);
        handler.movePiece(45, 39);
        handler.movePiece(21, 6);
        handler.movePiece(61, 34);
        handler.movePiece(14, 22);
        handler.movePiece(39, 13);
        assertEquals(true, handler.getCheckMate());
    }
}
