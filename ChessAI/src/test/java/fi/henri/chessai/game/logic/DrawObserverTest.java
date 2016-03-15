/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class DrawObserverTest {

    private ChessBoard board;
    private DrawObserver observer;
    private CheckMateObserver CMObserver;
    private char w;
    private char b;

    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.CMObserver = new CheckMateObserver(board);
        this.observer = new DrawObserver(board, CMObserver);
    }

    @Test
    public void ifTwoPiecesOnBoardDraw() {
        w = 1;
        b = 13;
        board.attemptToPlacePiece(w, 0);
        board.attemptToPlacePiece(b, 1);
        assertEquals(true, observer.isDraw());
    }

    @Test
    public void ifKingsAndKnightOnBoardDrawWhite() {
        w = 12;
        b = 24;
        char k = 6;
        board.attemptToPlacePiece(w, 0);
        board.attemptToPlacePiece(b, 1);
        board.attemptToPlacePiece(k, 2);
        assertEquals(true, observer.isDraw());
    }

    @Test
    public void ifKingsAndKnightOnBoardDrawBlack() {
        w = 12;
        b = 24;
        char k = 18;
        board.attemptToPlacePiece(w, 0);
        board.attemptToPlacePiece(b, 1);
        board.attemptToPlacePiece(k, 2);
        assertEquals(true, observer.isDraw());
    }

    @Test
    public void ifKingsAndBishopOnBoardDrawWhite() {
        w = 12;
        b = 24;
        char k = 8;
        board.attemptToPlacePiece(w, 0);
        board.attemptToPlacePiece(b, 1);
        board.attemptToPlacePiece(k, 2);
        assertEquals(true, observer.isDraw());
    }

    @Test
    public void ifKingsAndBishopOnBoardDrawBlack() {
        w = 12;
        b = 24;
        char k = 20;
        board.attemptToPlacePiece(w, 0);
        board.attemptToPlacePiece(b, 1);
        board.attemptToPlacePiece(k, 2);
        assertEquals(true, observer.isDraw());
    }

    @Test
    public void ifKingsAndTwoBishopsOnWhiteSquaresOnBoardDraw() {
        w = 12;
        b = 24;
        char k = 20;
        char d = 8;
        board.attemptToPlacePiece(w, 2);
        board.attemptToPlacePiece(b, 1);
        board.attemptToPlacePiece(k, 0);
        board.attemptToPlacePiece(d, 63);
        assertEquals(true, observer.isDraw());
    }

    @Test
    public void ifKingsAndTwoBishopsOnBlackSquaresOnBoardDraw() {
        w = 12;
        b = 24;
        char k = 20;
        char d = 8;
        board.attemptToPlacePiece(w, 2);
        board.attemptToPlacePiece(b, 1);
        board.attemptToPlacePiece(k, 7);
        board.attemptToPlacePiece(d, 56);
        assertEquals(true, observer.isDraw());
    }

    @Test
    public void ifKingsAndTwoBishopsOnDifferentSquaresItIsNotDraw() {
        w = 12;
        b = 24;
        char k = 20;
        char d = 8;
        board.attemptToPlacePiece(w, 2);
        board.attemptToPlacePiece(b, 1);
        board.attemptToPlacePiece(k, 0);
        board.attemptToPlacePiece(d, 7);
        assertEquals(false, observer.isDraw());
    }

    @Test
    public void ifKingsAndBishopPlusRandomNotDraw() {
        w = 12;
        b = 24;
        char k = 22;
        char d = 8;
        board.attemptToPlacePiece(w, 2);
        board.attemptToPlacePiece(b, 1);
        board.attemptToPlacePiece(k, 0);
        board.attemptToPlacePiece(d, 63);
        assertEquals(false, observer.isDraw());
    }

    @Test
    public void ifKingsAndPlusTwoRandomNotDraw() {
        w = 12;
        b = 24;
        char k = 22;
        char d = 1;
        board.attemptToPlacePiece(w, 2);
        board.attemptToPlacePiece(b, 1);
        board.attemptToPlacePiece(k, 0);
        board.attemptToPlacePiece(d, 63);
        assertEquals(false, observer.isDraw());
    }

    @Test
    public void fivePiecesNotDraw() {
        w = 12;
        b = 24;
        char k = 22;
        char d = 1;
        char t = 3;
        board.attemptToPlacePiece(w, 2);
        board.attemptToPlacePiece(b, 1);
        board.attemptToPlacePiece(k, 0);
        board.attemptToPlacePiece(d, 63);
        board.attemptToPlacePiece(t, 4);
        assertEquals(false, observer.isDraw());
    }

    @Test
    public void whenPiecesAreMovedHundredTimesItIsDraw() {
        w = 1;
        b = 13;
        board.attemptToPlacePiece(w, 0);
        board.attemptToPlacePiece(b, 63);
        for (int i = 0; i < 25; i++) {
            board.attemptToMovePiece(0, 1);
            board.attemptToMovePiece(63, 62);
            board.attemptToMovePiece(1, 0);
            board.attemptToMovePiece(62, 63);
        }
        assertEquals(true, observer.isDraw());
    }

    @Test
    public void whenPiecesAreMovedninetynineTimesItIsNotDraw() {
        w = 1;
        b = 13;
        board.attemptToPlacePiece(w, 0);
        board.attemptToPlacePiece(b, 63);
        for (int i = 0; i < 24; i++) {
            board.attemptToMovePiece(0, 1);
            board.attemptToMovePiece(63, 62);
            board.attemptToMovePiece(1, 0);
            board.attemptToMovePiece(62, 63);
        }
        board.attemptToMovePiece(0, 1);
        board.attemptToMovePiece(63, 62);
        board.attemptToMovePiece(1, 0);
        assertEquals(false, observer.isDraw());
    }
}
// Three fold repetition is tested in logicHandlerTest
// King alone and cannot move checked in check Mate observer.