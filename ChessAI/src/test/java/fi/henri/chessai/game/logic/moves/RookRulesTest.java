/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.moves;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class RookRulesTest {
    private ChessBoard board;
    private RookRules rRules;
    private char w;
    private char b;
    
    @Before 
    public void setUp() {
        this.board = new ChessBoard();
        this.rRules = new RookRules(board);
        this.w = 2;
        this.b = 22;
        this.board.attemptToPlacePiece(w, 27);
        this.board.attemptToPlacePiece(b, 28);
    }
    
    @Test
    public void rookCanMoveHorizontally() {
        assertEquals(true, rRules.commitIfMoveIsLegal(27, 24));
        assertEquals(true, rRules.commitIfMoveIsLegal(28, 31));
    }
    
    @Test
    public void rookCanMoveVertically() {
        assertEquals(true, rRules.commitIfMoveIsLegal(27, 3));
        assertEquals(true, rRules.commitIfMoveIsLegal(28, 60));
    }
    
    @Test
    public void rookCanNotMoveHorizontallyWhenSomethingIsBlocking() {
        assertEquals(false, rRules.commitIfMoveIsLegal(27, 31));
    }
    
    @Test
    public void rookCanNotMoveVerticallyIfSomethingIsBlocking() {
        board.attemptToPlacePiece(w, 36);
        assertEquals(false, rRules.commitIfMoveIsLegal(36, 4));
    }
}
