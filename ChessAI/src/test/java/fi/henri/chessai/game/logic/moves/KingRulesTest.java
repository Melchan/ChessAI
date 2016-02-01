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
public class KingRulesTest {

    private ChessBoard board;
    private KingRules kRules;
    private char w;
    private char b;

    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.kRules = new KingRules(board);
        this.w = 1;
        this.b = 21;
        this.board.attemptToPlacePiece(w, 27);
        this.board.attemptToPlacePiece(b, 28);
        this.board.attemptToPlacePiece(w, 56);
        this.board.attemptToPlacePiece(b, 0);
        this.board.attemptToPlacePiece(w, 63);
        this.board.attemptToPlacePiece(b, 7);
        this.board.attemptToPlacePiece(w, 60);
        this.board.attemptToPlacePiece(b, 4);
    }

    @Test
    public void kingCanMoveHorizontally() {
        assertEquals(true, kRules.commitIfMoveIsLegal(27, 26));
        assertEquals(true, kRules.commitIfMoveIsLegal(28, 29));
    }

    @Test
    public void kingCanMoveVertically() {
        assertEquals(true, kRules.commitIfMoveIsLegal(27, 19));
        assertEquals(true, kRules.commitIfMoveIsLegal(28, 36));
    }

    @Test
    public void kingCanMoveDiaconallyUp() {
        assertEquals(true, kRules.commitIfMoveIsLegal(27, 18));
        assertEquals(true, kRules.commitIfMoveIsLegal(28, 21));
    }

    @Test
    public void kingCanMoveDiaconallyDown() {
        assertEquals(true, kRules.commitIfMoveIsLegal(27, 34));
        assertEquals(true, kRules.commitIfMoveIsLegal(28, 37));
    }

    @Test
    public void kingCannotMoveMoreThanOneSquare() {
        assertEquals(false, kRules.commitIfMoveIsLegal(27, 25));
        assertEquals(false, kRules.commitIfMoveIsLegal(27, 11));
        assertEquals(false, kRules.commitIfMoveIsLegal(27, 9));
        assertEquals(false, kRules.commitIfMoveIsLegal(27, 41));
    }

    @Test
    public void castlingLeftSide() {
        assertEquals(true, kRules.commitIfMoveIsLegal(60, 58));
        assertEquals(true, kRules.commitIfMoveIsLegal(4, 2));
    }

    @Test
    public void castlingRightSide() {
        assertEquals(true, kRules.commitIfMoveIsLegal(60, 62));
        assertEquals(true, kRules.commitIfMoveIsLegal(4, 6));
    }

    @Test
    public void cantCastleWhenSomeThingIsBetweenTwoPiecesRightSide() {
        this.board.attemptToPlacePiece(w, 61);
        this.board.attemptToPlacePiece(b, 5);
        assertEquals(false, kRules.commitIfMoveIsLegal(60, 62));
        assertEquals(false, kRules.commitIfMoveIsLegal(4, 6));
    }

    @Test
    public void cantCastleWhenSomeThingIsBetweenTwoPiecesLeftSide() {
        this.board.attemptToPlacePiece(w, 59);
        this.board.attemptToPlacePiece(b, 3);
        assertEquals(false, kRules.commitIfMoveIsLegal(60, 58));
        assertEquals(false, kRules.commitIfMoveIsLegal(4, 2));
    }

    @Test
    public void whenCastlingRookMovesAsItShould() {
        kRules.commitIfMoveIsLegal(60, 58);
        kRules.commitIfMoveIsLegal(4, 6);
        assertEquals(0, board.getSquareContent(7));
        assertEquals(0, board.getSquareContent(56));
        assertEquals(2, board.getSquareContent(59));
        assertEquals(22, board.getSquareContent(5));
    }

    @Test
    public void noWeirdCastling() {
        board.setPieceToMoved(60);
        assertEquals(false, kRules.commitIfMoveIsLegal(60, 58));
    }

    @Test
    public void kingCannotMoveLikeSuperKnight() {
        assertEquals(false, kRules.commitIfMoveIsLegal(63, 6));
    }

    @Test
    public void whenKingMovesFirstTimeWillSetRooksToMoved() {
        assertEquals(true, kRules.movePiece(60, 52));
        assertEquals(true, kRules.movePiece(4, 12));
        assertEquals(true, board.hasPieceMovedInSquare(0));
        assertEquals(true, board.hasPieceMovedInSquare(7));
        assertEquals(true, board.hasPieceMovedInSquare(63));
        assertEquals(true, board.hasPieceMovedInSquare(53));
    }

    @Test
    public void whenKingSetsRooksMovedWhenMovedHeDoesntCreatePawns() {
        assertEquals(true, kRules.movePiece(63, 55));
        assertEquals(true, kRules.movePiece(0, 8));
        assertEquals(true, kRules.movePiece(56, 48));
        assertEquals(true, kRules.movePiece(7, 15));
        assertEquals(true, kRules.movePiece(60, 52));
        assertEquals(true, kRules.movePiece(4, 12));
        char empty = 0;
        assertEquals(empty, board.getSquareContent(63));
        assertEquals(empty, board.getSquareContent(0));
        assertEquals(empty, board.getSquareContent(56));
        assertEquals(empty, board.getSquareContent(7));
    }
}
