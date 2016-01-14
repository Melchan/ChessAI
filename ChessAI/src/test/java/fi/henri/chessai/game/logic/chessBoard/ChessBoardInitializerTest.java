/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.chessBoard;

import static fi.henri.chessai.game.logic.chessBoard.ChessPieces.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class ChessBoardInitializerTest {

    private ChessBoard board;
    private ChessBoardInitializer initializer;

    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.initializer = new ChessBoardInitializer(board);
        this.initializer.newGame();
    }

    @Test
    public void whitePawnsAreOnTheirPlaceAndUnMoven() {
        for (int i = 0; i < 8; i++) {
            int[] c = {i, 1};
            int l = board.coordinatesToIndex(c);
            char p = board.getSquareContent(l);
            assertEquals(WHITEPAWN, board.boardCharToChessPiece(p));
            assertEquals(false, board.hasPieceMovedInSquare(l));
        }
    }

    @Test
    public void blackPawnsAreOnTheirPlaceAndUnMoven() {
        for (int i = 0; i < 8; i++) {
            int[] c = {i, 6};
            int l = board.coordinatesToIndex(c);
            char p = board.getSquareContent(l);
            assertEquals(BLACKPAWN, board.boardCharToChessPiece(p));
            assertEquals(false, board.hasPieceMovedInSquare(l));
        }
    }

    @Test
    public void whiteRooksAreOnTheirPlaceAndUnMoven() {
        int[] c = {0, 0};
        int l = board.coordinatesToIndex(c);
        char p = board.getSquareContent(l);
        assertEquals(WHITEROOK, board.boardCharToChessPiece(p));
        assertEquals(false, board.hasPieceMovedInSquare(l));
        
        int[] a = {7, 0};
        int b = board.coordinatesToIndex(a);
        char x = board.getSquareContent(b);
        assertEquals(WHITEROOK, board.boardCharToChessPiece(x));
        assertEquals(false, board.hasPieceMovedInSquare(b));
    }

    @Test
    public void blackRooksAreOnTheirPlaceAndUnMoven() {
        int[] c = {0, 7};
        int l = board.coordinatesToIndex(c);
        char p = board.getSquareContent(l);
        assertEquals(BLACKROOK, board.boardCharToChessPiece(p));
        assertEquals(false, board.hasPieceMovedInSquare(l));
        
        int[] a = {7, 7};
        int b = board.coordinatesToIndex(a);
        char x = board.getSquareContent(b);
        assertEquals(BLACKROOK, board.boardCharToChessPiece(x));
        assertEquals(false, board.hasPieceMovedInSquare(b));
    }

    @Test
    public void whiteKnightsAreOnTheirPlaceAndMoven() {
        int[] c = {1, 0};
        int l = board.coordinatesToIndex(c);
        char p = board.getSquareContent(l);
        assertEquals(WHITEKNIGHT, board.boardCharToChessPiece(p));
        assertEquals(true, board.hasPieceMovedInSquare(l));
        
        int[] a = {6, 0};
        int b = board.coordinatesToIndex(a);
        char x = board.getSquareContent(b);
        assertEquals(WHITEKNIGHT, board.boardCharToChessPiece(x));
        assertEquals(true, board.hasPieceMovedInSquare(b));
    }

    @Test
    public void blackKnightsAreOnTheirPlaceAndMoven() {
        int[] c = {1, 7};
        int l = board.coordinatesToIndex(c);
        char p = board.getSquareContent(l);
        assertEquals(BLACKKNIGHT, board.boardCharToChessPiece(p));
        assertEquals(true, board.hasPieceMovedInSquare(l));
        
        int[] a = {6, 7};
        int b = board.coordinatesToIndex(a);
        char x = board.getSquareContent(b);
        assertEquals(BLACKKNIGHT, board.boardCharToChessPiece(x));
        assertEquals(true, board.hasPieceMovedInSquare(b));
    }

    @Test
    public void whiteBishopsAreOnTheirPlaceAndMoven() {
        int[] c = {2, 0};
        int l = board.coordinatesToIndex(c);
        char p = board.getSquareContent(l);
        assertEquals(WHITEBISHOP, board.boardCharToChessPiece(p));
        assertEquals(true, board.hasPieceMovedInSquare(l));
        
        int[] a = {5, 0};
        int b = board.coordinatesToIndex(a);
        char x = board.getSquareContent(b);
        assertEquals(WHITEBISHOP, board.boardCharToChessPiece(x));
        assertEquals(true, board.hasPieceMovedInSquare(b));
    }

    @Test
    public void blackBishopsAreOnTheirPlaceAndMoven() {
        int[] c = {2, 7};
        int l = board.coordinatesToIndex(c);
        char p = board.getSquareContent(l);
        assertEquals(BLACKBISHOP, board.boardCharToChessPiece(p));
        assertEquals(true, board.hasPieceMovedInSquare(l));
        
        int[] a = {5, 7};
        int b = board.coordinatesToIndex(a);
        char x = board.getSquareContent(b);
        assertEquals(BLACKBISHOP, board.boardCharToChessPiece(x));
        assertEquals(true, board.hasPieceMovedInSquare(b));
    }

    @Test
    public void queesAreOnTheirPlaceAndMoven() {
        int[] c = {3, 0};
        int l = board.coordinatesToIndex(c);
        char p = board.getSquareContent(l);
        assertEquals(WHITEQUEEN, board.boardCharToChessPiece(p));
        assertEquals(true, board.hasPieceMovedInSquare(l));
        
        int[] a = {3, 7};
        int b = board.coordinatesToIndex(a);
        char x = board.getSquareContent(b);
        assertEquals(BLACKQUEEN, board.boardCharToChessPiece(x));
        assertEquals(true, board.hasPieceMovedInSquare(b));
    }

    @Test
    public void kingsAreOnTheirPlaceAndUnMoven() {
        int[] c = {4, 0};
        int l = board.coordinatesToIndex(c);
        char p = board.getSquareContent(l);
        assertEquals(WHITEKING, board.boardCharToChessPiece(p));
        assertEquals(false, board.hasPieceMovedInSquare(l));
        
        int[] a = {4, 7};
        int b = board.coordinatesToIndex(a);
        char x = board.getSquareContent(b);
        assertEquals(BLACKKING, board.boardCharToChessPiece(x));
        assertEquals(false, board.hasPieceMovedInSquare(b));
    }
    
    @Test
    public void BoardIsEmptiedBeforePLacingNewPiecesWhenNewGameIsCalled() {
        board.attemptToMovePiece(57, 42);
        initializer.newGame();
        char p = board.getSquareContent(42);
        assertEquals(NOTCHESSPIECE, board.boardCharToChessPiece(p));
    }
}
