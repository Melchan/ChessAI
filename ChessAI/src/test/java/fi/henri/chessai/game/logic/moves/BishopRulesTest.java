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
public class BishopRulesTest {

    private ChessBoard board;
    private BishopRules bRules;
    private char w;
    private char b;

    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.bRules = new BishopRules(board);
        this.w = 2;
        this.b = 22;
        this.board.attemptToPlacePiece(w, 27);
        this.board.attemptToPlacePiece(b, 28);
    }

    @Test
    public void bishopCanMoveLeftUp() {
        assertEquals(true, bRules.commitIfMoveIsLegal(27, 0));
        assertEquals(true, bRules.commitIfMoveIsLegal(28, 1));
    }

    @Test
    public void bishopCanMoveLeftDown() {
        assertEquals(true, bRules.commitIfMoveIsLegal(27, 48));
        assertEquals(true, bRules.commitIfMoveIsLegal(28, 56));
    }

    @Test
    public void bishopCanMoveRightUp() {
        assertEquals(true, bRules.commitIfMoveIsLegal(27, 6));
        assertEquals(true, bRules.commitIfMoveIsLegal(28, 7));
    }

    @Test
    public void bzishopCanMoveRightDown() {
        assertEquals(true, bRules.commitIfMoveIsLegal(27, 63));
        assertEquals(true, bRules.commitIfMoveIsLegal(28, 55));
    }

    @Test
    public void bishopCantMoveHorizontallyOrVertically() {
        assertEquals(false, bRules.commitIfMoveIsLegal(27, 24));
        assertEquals(false, bRules.commitIfMoveIsLegal(28, 31));
        assertEquals(false, bRules.commitIfMoveIsLegal(27, 3));
        assertEquals(false, bRules.commitIfMoveIsLegal(28, 60));
    }

    @Test
    public void bishopCantMoveWhenSomeoneIsBlocking() {
        board.attemptToPlacePiece(b, 9);
        assertEquals(false, bRules.commitIfMoveIsLegal(27, 0));
    }
    
    @Test 
    public void noMagicMove() {
        board.attemptToPlacePiece(w, 40);
        board.attemptToPlacePiece(b, 6);
        assertEquals(false, bRules.commitIfMoveIsLegal(40, 6));
    }
}
