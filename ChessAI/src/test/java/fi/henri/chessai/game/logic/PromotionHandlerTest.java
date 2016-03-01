/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class PromotionHandlerTest {
    private ChessBoard board;
    private PromotionHandler handler;
    private char b;
    private char w;
    private char bQueen;
    private char wQueen;
    
    @Before
    public void setUp() {
        board = new ChessBoard();
        handler = new PromotionHandler(board);
        b = 14;
        w = 2;
        bQueen = 22;
        wQueen = 10;
    }
    
    @Test
    public void doesItPromoteCorrectlyWhitePieces() {
        assertTrue(board.attemptToPlacePiece(w, 0));
        assertTrue(board.attemptToPlacePiece(w, 7));
        assertTrue(board.attemptToPlacePiece(w, 8));
        handler.checkAndPromote();
        
        assertEquals(wQueen, board.getSquareContent(0));
        assertEquals(wQueen, board.getSquareContent(7));
        assertEquals(w, board.getSquareContent(8));
    }
    
    @Test
    public void doesItPromoteCorrectlyBlackPieces() {
        assertTrue(board.attemptToPlacePiece(b, 56));
        assertTrue(board.attemptToPlacePiece(b, 63));
        assertTrue(board.attemptToPlacePiece(b, 55));
        handler.checkAndPromote();
        
        assertEquals(bQueen, board.getSquareContent(56));
        assertEquals(bQueen, board.getSquareContent(63));
        assertEquals(b, board.getSquareContent(55));
    }
}
