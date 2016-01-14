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
public class QueenRulesTest {

    private ChessBoard board;
    private QueenRules qRules;
    private char w;
    private char b;

    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.qRules = new QueenRules(board);
        this.w = 2;
        this.b = 22;
        this.board.attemptToPlacePiece(w, 27);
        this.board.attemptToPlacePiece(b, 28);
    }

    @Test
    public void queenCanMoveRookMoves() {
        assertEquals(true, qRules.commitIfMoveIsLegal(27, 24));
        assertEquals(true, qRules.commitIfMoveIsLegal(28, 31));
    }

    @Test
    public void queenCanMoveBishopMoves() {
        assertEquals(true, qRules.commitIfMoveIsLegal(27, 0));
        assertEquals(true, qRules.commitIfMoveIsLegal(28, 55));
    }

    @Test
    public void queenCantMoveKnightMoves() {
        assertEquals(false, qRules.commitIfMoveIsLegal(27, 12));
    }

}
