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
public class KnightRulesTest {
    private ChessBoard board;
    private KnightRules kRules;
    private char w;
    private char b;

    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.kRules = new KnightRules(board);
        this.w = 2;
        this.b = 22;
        this.board.attemptToPlacePiece(w, 27);
        this.board.attemptToPlacePiece(b, 28);
    }
    
    @Test
    public void knightCanMoveUp() {
        assertEquals(true, kRules.commitIfMoveIsLegal(27, 10));
        assertEquals(true, kRules.commitIfMoveIsLegal(28, 13));
    }
    
    @Test
    public void knightCanMoveRight() {
        assertEquals(true, kRules.commitIfMoveIsLegal(27, 21));
        assertEquals(true, kRules.commitIfMoveIsLegal(28, 38));
    }
    
    @Test
    public void knightCanMoveDown() {
        assertEquals(true, kRules.commitIfMoveIsLegal(27, 42));
        assertEquals(true, kRules.commitIfMoveIsLegal(28, 45));
    }
    
    @Test
    public void knightCanMoveLeft() {
        assertEquals(true, kRules.commitIfMoveIsLegal(27, 17));
        assertEquals(true, kRules.commitIfMoveIsLegal(28, 34));
    }   
    
    @Test
    public void knightCantMoveLikeBishop() {
        assertEquals(false, kRules.commitIfMoveIsLegal(27, 0));
        assertEquals(false, kRules.commitIfMoveIsLegal(28, 56));
        assertEquals(false, kRules.commitIfMoveIsLegal(27, 6));
        assertEquals(false, kRules.commitIfMoveIsLegal(28, 55));
    }
    
    @Test
    public void knightCantMoveLikeRook() {
        assertEquals(false, kRules.commitIfMoveIsLegal(27, 24));
        assertEquals(false, kRules.commitIfMoveIsLegal(28, 31));
        assertEquals(false, kRules.commitIfMoveIsLegal(27, 3));
        assertEquals(false, kRules.commitIfMoveIsLegal(28, 60));
    }
}
