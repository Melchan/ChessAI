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
public class PawnRulesTest {

    private ChessBoard board;
    private PawnRules pRules;
    private char w;
    private char b;

    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.pRules = new PawnRules(board);
        this.w = 1;
        this.b = 13;
        this.board.attemptToPlacePiece(w, 49);
        this.board.attemptToPlacePiece(b, 14);
    }

    @Test
    public void pawnCanMoveForWardOneSpace() {
        assertEquals(true, pRules.commitIfMoveIsLegal(49, 41));
        assertEquals(true, pRules.commitIfMoveIsLegal(14, 22));
    }

    @Test
    public void pawnCanMarch() {
        assertEquals(true, pRules.commitIfMoveIsLegal(49, 33));
        assertEquals(true, pRules.commitIfMoveIsLegal(14, 30));
    }

    @Test
    public void whitePawnCanOnlyMoveForward() {
        assertEquals(false, pRules.commitIfMoveIsLegal(49, 40));
        assertEquals(false, pRules.commitIfMoveIsLegal(49, 42));
        assertEquals(false, pRules.commitIfMoveIsLegal(49, 48));
        assertEquals(false, pRules.commitIfMoveIsLegal(49, 50));
        assertEquals(false, pRules.commitIfMoveIsLegal(49, 56));
        assertEquals(false, pRules.commitIfMoveIsLegal(49, 58));
        assertEquals(false, pRules.commitIfMoveIsLegal(49, 57));
    }

    @Test
    public void blackPawnCanOnlyMoveForward() {
        pRules.commitIfMoveIsLegal(49, 33);

        assertEquals(false, pRules.commitIfMoveIsLegal(14, 5));
        assertEquals(false, pRules.commitIfMoveIsLegal(14, 6));
        assertEquals(false, pRules.commitIfMoveIsLegal(14, 7));
        assertEquals(false, pRules.commitIfMoveIsLegal(14, 13));
        assertEquals(false, pRules.commitIfMoveIsLegal(14, 15));
        assertEquals(false, pRules.commitIfMoveIsLegal(14, 21));
        assertEquals(false, pRules.commitIfMoveIsLegal(14, 23));
    }

    @Test
    public void pawnCanEatEnemyPiece() {
        board.attemptToPlacePiece(w, 21);
        board.attemptToPlacePiece(b, 42);

        assertEquals(true, pRules.commitIfMoveIsLegal(49, 42));
        assertEquals(true, pRules.commitIfMoveIsLegal(14, 21));
    }

    @Test
    public void enPassantIsRecordedCorrectlyAtSidesWhite() {
        board.attemptToPlacePiece(w, 48);
        board.attemptToPlacePiece(b, 31);

        pRules.commitIfMoveIsLegal(48, 32);
        assertEquals(0, board.getEnPassantChange());
    }

    @Test
    public void enPassantIsRecordedCorrectlyAtSidesBlack() {
        board.attemptToPlacePiece(w, 32);
        board.attemptToPlacePiece(b, 15);

        pRules.commitIfMoveIsLegal(49, 41);
        pRules.commitIfMoveIsLegal(15, 31);
        assertEquals(0, board.getEnPassantChange());
    }

    @Test
    public void enPassantIsNotRecordedWhenFriendIsSittingNextToYou() {
        board.attemptToPlacePiece(w, 32);
        board.attemptToPlacePiece(b, 30);

        pRules.commitIfMoveIsLegal(49, 33);
        assertEquals(0, board.getEnPassantChange());
        pRules.commitIfMoveIsLegal(14, 30);
        assertEquals(0, board.getEnPassantChange());
    }

    @Test
    public void enPassantChangeIsRecordedCorrectly() {
        board.attemptToPlacePiece(w, 31);
        board.attemptToPlacePiece(b, 32);

        pRules.commitIfMoveIsLegal(49, 33);
        assertEquals(33, board.getEnPassantChange());
        pRules.commitIfMoveIsLegal(14, 30);
        assertEquals(30, board.getEnPassantChange());
    }

    @Test
    public void canCommitEnPassant() {
        board.attemptToPlacePiece(w, 31);
        board.attemptToPlacePiece(b, 32);
        board.attemptToPlacePiece(w, 8);

        pRules.commitIfMoveIsLegal(49, 33);
        assertEquals(true, pRules.commitIfMoveIsLegal(32, 41));
        assertEquals(true, pRules.commitIfMoveIsLegal(8, 0));
        pRules.commitIfMoveIsLegal(14, 30);
        assertEquals(true, pRules.commitIfMoveIsLegal(31, 22));
    }
    
    @Test
    public void cannotMarchWhenOtherPiecesOnTheWay() {
        board.attemptToPlacePiece(w, 41);
        board.attemptToPlacePiece(b, 22);
        
        assertEquals(false, pRules.commitIfMoveIsLegal(49, 33));
        assertEquals(false, pRules.commitIfMoveIsLegal(14, 30));
    }
    
    @Test
    public void whenEnPassantCommitedPieceIsEaten() {
        board.attemptToPlacePiece(w, 31);
        board.attemptToPlacePiece(b, 32);
        board.attemptToPlacePiece(w, 8);

        pRules.commitIfMoveIsLegal(49, 33);
        pRules.commitIfMoveIsLegal(32, 41);
        assertEquals(0, board.getSquareContent(33));
        pRules.commitIfMoveIsLegal(8, 0);
        pRules.commitIfMoveIsLegal(14, 30);
        pRules.commitIfMoveIsLegal(31, 22);
        assertEquals(0, board.getSquareContent(30));
    }
}