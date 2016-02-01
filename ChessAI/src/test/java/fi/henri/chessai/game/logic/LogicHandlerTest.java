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
    }

    @Test
    public void fastGameStartToFinnishEndingToWhiteVictory() {
        assertEquals(true, handler.movePiece(52, 36));
        assertEquals(true, handler.movePiece(13, 29));
        assertEquals(true, handler.movePiece(59, 45));
        assertEquals(true, handler.movePiece(6, 21));
        assertEquals(true, handler.movePiece(45, 29));
        assertEquals(true, handler.movePiece(21, 6));
        assertEquals(true, handler.movePiece(61, 34));
        assertEquals(true, handler.movePiece(14, 22));
        assertEquals(true, handler.movePiece(29, 13));
        assertEquals(true, handler.getCheckMate());
    }

    @Test
    public void fastGameStartToFinnishEndingToBlackVictory() {
        assertEquals(true, handler.movePiece(52, 36));
        assertEquals(true, handler.movePiece(12, 28));
        assertEquals(true, handler.movePiece(61, 34));
        assertEquals(true, handler.movePiece(5, 26));
        assertEquals(true, handler.movePiece(59, 31));
        assertEquals(true, handler.movePiece(6, 21));
        assertEquals(true, handler.movePiece(31, 13));
        assertEquals(true, handler.getCheckMate());
    }

    @Test
    public void gameTest() {
        assertEquals(true, handler.movePiece(50, 34));
        assertEquals(true, handler.movePiece(10, 26));
        assertEquals(true, handler.movePiece(57, 42));
        assertEquals(true, handler.movePiece(6, 21));
        assertEquals(true, handler.movePiece(62, 45));
        assertEquals(true, handler.movePiece(14, 22));
        assertEquals(true, handler.movePiece(51, 35));
        assertEquals(true, handler.movePiece(26, 35));
        assertEquals(true, handler.movePiece(45, 35));
        assertEquals(true, handler.movePiece(5, 14));
        assertEquals(true, handler.movePiece(54, 46));
        assertEquals(true, handler.movePiece(4, 6));
        assertEquals(true, handler.movePiece(61, 54));
        assertEquals(true, handler.movePiece(1, 18));
        assertEquals(true, handler.movePiece(58, 44));
        assertEquals(true, handler.movePiece(12, 28));
        assertEquals(true, handler.movePiece(35, 18));
        assertEquals(true, handler.movePiece(9, 18));
        assertEquals(true, handler.movePiece(60, 62));
        assertEquals(true, handler.movePiece(2, 9));
        assertEquals(true, handler.movePiece(59, 19));
        assertEquals(true, handler.movePiece(21, 4));
        assertEquals(true, handler.movePiece(19, 40));
        assertEquals(true, handler.movePiece(11, 27));
        assertEquals(true, handler.movePiece(56, 59));
        assertEquals(true, handler.movePiece(4, 10));
        assertEquals(true, handler.movePiece(34, 27));
        assertEquals(true, handler.movePiece(18, 27));
        assertEquals(true, handler.movePiece(44, 26));
        assertEquals(true, handler.movePiece(5, 4));
        assertEquals(true, handler.movePiece(42, 27));
        assertEquals(true, handler.movePiece(10, 27));
        assertEquals(true, handler.movePiece(52, 36));
        assertEquals(true, handler.movePiece(15, 31));
        assertEquals(true, handler.movePiece(36, 27));
        assertEquals(true, handler.movePiece(28, 36));
        assertEquals(true, handler.movePiece(27, 19));
        assertEquals(true, handler.movePiece(9, 18));
        assertEquals(true, handler.movePiece(61, 60));
        assertEquals(true, handler.movePiece(8, 16));
        assertEquals(true, handler.movePiece(40, 33));
        assertEquals(true, handler.movePiece(13, 29));
        assertEquals(true, handler.movePiece(59, 51));
        assertEquals(true, handler.movePiece(0, 1));
        assertEquals(true, handler.movePiece(33, 34));
        assertEquals(true, handler.movePiece(6, 15));
        assertEquals(true, handler.movePiece(60, 59));
        assertEquals(true, handler.movePiece(18, 25));
        assertEquals(true, handler.movePiece(34, 13));
        assertEquals(true, handler.movePiece(15, 23));
        assertEquals(true, handler.movePiece(19, 11));
        assertEquals(true, handler.movePiece(4, 28));
        assertEquals(true, handler.movePiece(26, 35));
        assertEquals(true, handler.movePiece(28, 12));
        assertEquals(true, handler.movePiece(35, 14));
        assertEquals(true, handler.movePiece(23, 15));
        assertEquals(true, handler.movePiece(13, 12));
        assertEquals(true, handler.movePiece(3, 12));
        assertEquals(true, handler.movePiece(11, 3));
        assertEquals(true, handler.movePiece(1, 3));
        assertEquals(true, handler.movePiece(51, 3));
        assertEquals(true, handler.movePiece(12, 26));
        assertEquals(true, handler.movePiece(14, 5));
        assertEquals(true, handler.movePiece(26, 50));
        assertEquals(true, handler.movePiece(3, 51));
        assertEquals(true, handler.movePiece(50, 32));
        assertEquals(true, handler.movePiece(49, 41));
        assertEquals(true, handler.movePiece(32, 24));
        assertEquals(true, handler.movePiece(41, 33));
        assertEquals(true, handler.movePiece(24, 32));
        assertEquals(true, handler.movePiece(59, 58));
        assertEquals(true, handler.movePiece(25, 43));
        assertEquals(true, handler.movePiece(58, 10));
        assertEquals(true, handler.movePiece(15, 6));
        assertEquals(true, handler.movePiece(10, 2));
        assertEquals(true, handler.movePiece(32, 11));
        assertEquals(true, handler.movePiece(2, 0));
        assertEquals(true, handler.movePiece(11, 18));
        assertEquals(true, handler.movePiece(0, 1));
        assertEquals(true, handler.movePiece(18, 58));
        assertEquals(true, handler.movePiece(54, 61));
        assertEquals(true, handler.movePiece(58, 61));
        assertEquals(true, handler.getCheckMate());
    }

    @Test
    public void threeFoldRepetitionWorks() {
        assertEquals(true, handler.movePiece(62, 45));
        assertEquals(true, handler.movePiece(6, 21));
        assertEquals(true, handler.movePiece(45, 62));
        assertEquals(true, handler.movePiece(21, 6));
        assertEquals(true, handler.movePiece(62, 45));
        assertEquals(true, handler.movePiece(6, 21));
        assertEquals(true, handler.movePiece(45, 62));
        assertEquals(true, handler.movePiece(21, 6));
        assertEquals(true, handler.movePiece(62, 45));
        assertEquals(true, handler.getDraw());
    }
}
