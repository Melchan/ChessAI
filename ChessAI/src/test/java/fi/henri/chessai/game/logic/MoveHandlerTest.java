/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import static java.awt.Color.WHITE;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manhenri
 */
public class MoveHandlerTest {
    
    private ChessBoard board;
    private MoveHandler handler;
    private char wKing;
    private char bKing;
    private char wRook;
    private char bRook;
    
    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.handler = new MoveHandler(board);
        this.wKing = 11;
        this.bKing = 23;
        this.wRook = 3;
        this.bRook = 15;
    }
    
    @Test
    public void pieceCanMove() {
        board.attemptToPlacePiece(wKing, 4);
        assertEquals(true, handler.movePiece(4, 5));
    }
    
    @Test
    public void ifKingIsThreatenedMoveIsfalse() {
        board.attemptToPlacePiece(wKing, 4);
        board.attemptToPlacePiece(bRook, 61);
        assertEquals(false, handler.movePiece(4, 5));
    }
    
    @Test
    public void whenKingIsThreatenedCancelMove() {
        board.attemptToPlacePiece(wKing, 4);
        board.attemptToPlacePiece(bRook, 61);
        assertEquals(false, handler.movePiece(4, 5));
        assertEquals(WHITE, board.getTurn());
        assertEquals(wKing, board.getSquareContent(4));
    }
    
    @Test
    public void threatenersAreRecordedAndCanBeExtractedAfterMoveCancel() {
        board.attemptToPlacePiece(bKing, 4);
        board.attemptToPlacePiece(wRook, 60);
        handler.movePiece(60, 61);
        handler.movePiece(4, 5);
        assertEquals(1, handler.getTheateners().size());
    }
    
    @Test
    public void CastlingIsPossible() {
        this.board.attemptToPlacePiece(wKing, 60);
        this.board.attemptToPlacePiece(bKing, 4);
        this.board.attemptToPlacePiece(wRook, 56);
        this.board.attemptToPlacePiece(bRook, 0);
        assertEquals(true, handler.movePiece(60, 58));
        assertEquals(true, handler.movePiece(4, 2));
    }
    
    @Test
    public void CastlingCannotBeDoneWhenKingIsThreatened() {
        this.board.attemptToPlacePiece(wKing, 60);
        this.board.attemptToPlacePiece(wRook, 56);
        this.board.attemptToPlacePiece(bRook, 63);
        this.board.attemptToPlacePiece(bRook, 26);
        assertEquals(false, handler.movePiece(60, 58));
        board.attemptToEmptySquare(63);
        assertEquals(false, handler.movePiece(60, 58));
    }
    
    @Test
    public void ifCastlingFailsEverythingIsAsItWasBefore() {
        this.board.attemptToPlacePiece(wKing, 60);
        this.board.attemptToPlacePiece(wRook, 56);
        this.board.attemptToPlacePiece(bRook, 63);
        this.board.attemptToPlacePiece(bRook, 26);
        handler.movePiece(60, 58);
        board.attemptToEmptySquare(63);
        handler.movePiece(60, 58);
        for (int i = 57; i < 60;i++) {
            assertEquals(0, board.getSquareContent(i));
        }
    }
    
    @Test
    public void threatenersAreSavedCorrectlyIfSomeOneIsTryingToMoveWhenKingIsThreatened() {
        this.board.attemptToPlacePiece(wKing, 60);
        this.board.attemptToPlacePiece(wRook, 56);
        this.board.attemptToPlacePiece(bRook, 63);
        this.board.attemptToPlacePiece(bRook, 26);
        handler.movePiece(56, 32);
        assertEquals(1, handler.getTheateners().size());
    }
    
    @Test
    public void afterCastlingEverythingShouldBeNormal() {
        board.attemptToPlacePiece(bRook, 7);
        board.attemptToPlacePiece(bKing, 4);
        board.attemptToPlacePiece(bRook, 13);
        board.attemptToPlacePiece(bRook, 14);
        board.attemptToPlacePiece(bRook, 15);
        board.attemptToPlacePiece((char)8, 51);
        assertEquals(true, handler.movePiece(51, 58));
        assertEquals(true, handler.movePiece(4, 6));
        assertEquals(true, handler.movePiece(58, 40));
        assertEquals(true, handler.movePiece(5, 4));
    }
    
    public void canNotCastleWhenKingMovingOverThreatSquare() {
        board.attemptToPlacePiece(wKing, 4);
        board.attemptToPlacePiece(bRook, 34);
        board.attemptToPlacePiece(bRook, 35);
        board.attemptToPlacePiece(wRook, 0);
        assertEquals(false, handler.movePiece(4, 2));
    }
    
    public void castlingChecksAreOk() {
        board.attemptToPlacePiece(wKing, 52);
        board.attemptToPlacePiece(wRook, 56);
        board.attemptToPlacePiece(bKing, 4);
        board.attemptToPlacePiece(bRook, 8);
        handler.movePiece(52, 60);
        handler.movePiece(8, 0);
        assertEquals(false, handler.movePiece(60, 62));
        board.changeTurn();
        assertEquals(false, handler.movePiece(4, 2));  
    }
}