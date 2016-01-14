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
public class PieceMovementTest {

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
    public void pieceCantEatOwnPieces() {
        char w2 = 4;
        board.attemptToPlacePiece(w2, 24);
        assertEquals(false, rRules.movePiece(27, 24));
    }

    @Test
    public void pieceCanEatEnemyPiece() {
        board.attemptToPlacePiece(b, 24);
        assertEquals(true, rRules.movePiece(27, 24));
    }
    
    @Test
    public void pieceDoesNothingWithBadParameters() {
        assertEquals(false, rRules.movePiece(27, 27));
        assertEquals(false, rRules.movePiece(-1, 27));
        assertEquals(false, rRules.movePiece(27, 65));
    }
}
