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
public class MoveLibraryTest {
    private ChessBoard board;
    private MoveLibrary library;
    private char w;
    private char b;
    
    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.library = new MoveLibrary(board);
    }
    
    @Test
    public  void movePawn() {
        w = 1;
        b = 13;
        board.attemptToPlacePiece(w, 27);
        board.attemptToPlacePiece(b, 28);
        assertEquals(true, library.movePiece(27, 19));
        assertEquals(true, library.movePiece(28, 36));
    }
    
    @Test
    public void moveRook() {
        w = 3;
        b = 15;
        board.attemptToPlacePiece(w, 27);
        board.attemptToPlacePiece(b, 28);
        assertEquals(true, library.movePiece(27, 3));
        assertEquals(true, library.movePiece(28, 60));
    }
    
    @Test
    public void moveKnight() {
        w = 5;
        b = 17;
        board.attemptToPlacePiece(w, 27);
        board.attemptToPlacePiece(b, 28);
        assertEquals(true, library.movePiece(27, 10));
        assertEquals(true, library.movePiece(28, 45));
    }
    
    @Test
    public void moveBishop() {
        w = 7;
        b = 19;
        board.attemptToPlacePiece(w, 27);
        board.attemptToPlacePiece(b, 28);
        assertEquals(true, library.movePiece(27, 63));
        assertEquals(true, library.movePiece(28, 7));
    }
    
    @Test
    public void moveQueen() {
        w = 9;
        b = 21;
        board.attemptToPlacePiece(w, 27);
        board.attemptToPlacePiece(b, 28);
        assertEquals(true, library.movePiece(27, 3));
        assertEquals(true, library.movePiece(28, 7));
    }
    
    @Test
    public void moveKing() {
        w = 11;
        b = 23;
        board.attemptToPlacePiece(w, 27);
        board.attemptToPlacePiece(b, 28);
        assertEquals(true, library.movePiece(27, 35));
        assertEquals(true, library.movePiece(28, 37));
    }
    
    @Test
    public void cantMoveEmpty() {
        assertEquals(false, library.movePiece(0, 7));
    }
}
