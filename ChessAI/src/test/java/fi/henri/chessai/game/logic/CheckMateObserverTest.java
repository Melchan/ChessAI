/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import static java.awt.Color.WHITE;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class CheckMateObserverTest {
    private ChessBoard board;
    private CheckMateObserver observer;
    private char wKing;
    private char bKing;
    private char wQueen;
    private char bQueen;
    private char wPawn;
    private char bPawn;
    private char wRook;
    private char bRook;
    
    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.observer = new CheckMateObserver(board);
        this.wKing = 11;
        this.bKing = 23;
        this.wQueen = 10;
        this.bQueen = 22;
        this.wPawn = 1;
        this.bPawn = 13;
        this.wRook = 3;
        this.bRook = 15;
    }
    
    @Test
    public void whenKingIsThreatenedFromTwoDirectionsAtOnceWhenAloneHeIsInCheckMate() {
        board.attemptToPlacePiece(bKing, 4);
        board.attemptToPlacePiece(wQueen, 0);
        board.attemptToPlacePiece(wQueen, 21);
        board.attemptToMovePiece(21, 20);
        assertEquals(true, observer.isCheckMate());
    }
    
    @Test
    public void whenKingIsThreatenedFromTwoDirectionsHeCanMoveToSafetyNotCheckMate() {
        board.attemptToPlacePiece(bKing, 4);
        board.attemptToPlacePiece(wQueen, 0);
        board.attemptToPlacePiece(wQueen, 63);
        board.attemptToMovePiece(21, 17);
        assertEquals(false, observer.isCheckMate());
    } 
    
    @Test
    public void whenKingIsThreatenedFromOneDirectionHeCanMoveToSafety() {
        board.attemptToPlacePiece(wKing, 4);
        board.attemptToPlacePiece(bQueen, 0);
        assertEquals(false, observer.isCheckMate());
    }
    
    @Test
    public void whenKingThreatenedFromOneDirectionEnemyCanBeEatenToSaveKing() {
        board.attemptToPlacePiece(bKing, 0);
        board.attemptToPlacePiece(wPawn, 8);
        board.attemptToPlacePiece(wPawn, 9);
        board.attemptToPlacePiece(bRook, 14);
        board.attemptToPlacePiece(wRook, 7);
        assertEquals(false, observer.isCheckMate());
    }
    
    @Test
    public void whenKingIsThreatenedEnemyCanBeBlocked() {
        board.attemptToPlacePiece(bKing, 0);
        board.attemptToPlacePiece(wPawn, 8);
        board.attemptToPlacePiece(wPawn, 9);
        board.attemptToPlacePiece(bRook, 10);
        board.attemptToPlacePiece(wRook, 7);
        assertEquals(false, observer.isCheckMate());
    }
    
    @Test
    public void whenKingIsThreatenedSomeOneCanComeBetweenInCloseRange() {
                board.attemptToPlacePiece(wKing, 62);
                board.attemptToPlacePiece(wPawn, 53);
                board.attemptToPlacePiece(wPawn, 55);
                board.attemptToPlacePiece(wPawn, 46);
                board.attemptToPlacePiece(wQueen, 54);
                board.attemptToPlacePiece(bQueen, 58);
                board.attemptToPlacePiece(bQueen, 43);
                assertEquals(false, observer.isCheckMate());
    }
}