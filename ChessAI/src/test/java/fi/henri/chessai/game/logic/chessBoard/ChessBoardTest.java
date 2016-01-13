/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.chessBoard;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class ChessBoardTest {
    private ChessBoard board;
    private char a;
    private char b;
    
    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.a = 4;
        this.b = 9;
    }
    
    @Test
    public void whiteHasFirstTurn() {
        assertEquals(WHITE, board.getTurn());
    }
    
    @Test
    public void chessPieceCanOnlyBePlacedOnBoard() {
        a = 2;
        assertEquals(false, board.attemptToPlacePiece(a, 64));
        assertEquals(false, board.attemptToPlacePiece(a, -1));
    }
    
    @Test
    public void onlyChessPiecesCanBePlacedOnBoard() {
        b = 0;
        assertEquals(false, board.attemptToPlacePiece(b, 4));
        a = 25;
        assertEquals(false, board.attemptToPlacePiece(a, 4));
    }
    
    @Test
    public void canPlaceChessPieceOnBoard() {
        b = 1;
        assertEquals(true, board.attemptToPlacePiece(b, 4));
        a = 24;
        assertEquals(true, board.attemptToPlacePiece(a, 4));
    }
    
    @Test
    public void whenPieceIsPlacedItCanBefoundFromBoard() {
        b = 0;
        assertEquals(b, board.getSquareContent(4));
        b = 8;
        board.attemptToPlacePiece(b, 4);
        assertEquals(b, board.getSquareContent(4));
    }
    
    @Test
    public void cannotGiveCoordinatesOutsideOfBoardForMoveChessPieceMethod() {
        assertEquals(false, board.attemptToMovePiece(65, 2));
        assertEquals(false, board.attemptToMovePiece(-1, 2));
        assertEquals(false, board.attemptToMovePiece(2, 65));
        assertEquals(false, board.attemptToMovePiece(2, -1));
    }
    
    @Test
    public void ifChessPieceIsMovedItWillMove() {
        board.attemptToPlacePiece(a, 4);
        board.attemptToMovePiece(4, 20);
        assertEquals(a, board.getSquareContent(20));
    }
    
    @Test 
    public void whenPieceIsMovedSquareItLeftIsEmptiet() {
        b = 0;
        board.attemptToPlacePiece(a, 4);
        board.attemptToMovePiece(4, 20);
        assertEquals(b, board.getSquareContent(4));
    }
    
    @Test
    public void whenMoveIsDoneTurnChangesAccordingly() {
        board.attemptToPlacePiece(a, 4);
        board.attemptToMovePiece(4, 20);
        assertEquals(BLACK, board.getTurn());
        board.attemptToMovePiece(20, 41);
        assertEquals(WHITE, board.getTurn());
    }
    
    @Test
    public void rollBackWorks() {
        board.attemptToPlacePiece(a, 4);
        board.attemptToMovePiece(4, 20);
        board.rollBack(1);
        assertEquals(a, board.getSquareContent(4));
    }
    
    @Test
    public void cantRollBackMoreThanThereHasBeenTurns() {
        board.attemptToPlacePiece(a, 4);
        board.attemptToMovePiece(4, 20);
        board.attemptToMovePiece(20, 1);
        board.attemptToMovePiece(1, 2);
        board.attemptToMovePiece(2, 30);
        board.attemptToMovePiece(30, 21);
        board.attemptToMovePiece(21, 40);
        assertEquals(false, board.rollBack(7));
    }
    
    @Test
    public void rollBackWorksafterManyMoves() {
        board.attemptToPlacePiece(a, 4);
        board.attemptToMovePiece(4, 20);
        board.attemptToMovePiece(20, 1);
        board.attemptToMovePiece(1, 2);
        board.attemptToMovePiece(2, 30);
        board.attemptToMovePiece(30, 21);
        board.attemptToMovePiece(21, 40);
        board.rollBack(4);
        assertEquals(a, board.getSquareContent(1));
    }
    
    @Test
    public void rollBackClearsMoveHistory() {
        board.attemptToPlacePiece(a, 4);
        board.attemptToMovePiece(4, 20);
        board.attemptToMovePiece(20, 1);
        board.rollBack(2);
        board.attemptToMovePiece(4, 2);
        board.attemptToMovePiece(2, 30);
        board.attemptToMovePiece(30, 21);
        board.attemptToMovePiece(21, 40);
        board.rollBack(4);
        assertEquals(a, board.getSquareContent(4));
    }
    
    @Test
    public void indexToCoordinateWorks() {
        char[][] b = new char[8][8];
        for (int i = 0; i< 64;i++ ) {
           int[] c = board.indexToCoordinates(i);
           b[c[0]][c[1]] = a;
        }
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                assertEquals(a, b[x][y]);
            }
        }
    }
    
    @Test
    public void coordinateToIndexWorks() {
        char[] b = new char[64];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int[] c = {x, y};
                int i = board.coordinatesToIndex(c);
                b[i] = a;
            }
        }
        
        for (int i = 0; i< 64;i++ ) {
           assertEquals(a, b[i]);
        }
    }
    
    @Test
    public void attemptToEmptySquareDoesntWorkWithBacCoordinate() {
        b = 65;
        assertEquals(false, board.attemptToEmptySquare(b));
    }
    
    @Test
    public void attemptToEmptySquareEmptiesSquare() {
        board.attemptToPlacePiece(a, 20);
        board.attemptToEmptySquare(20);
        b = 0;
        assertEquals(b, board.getSquareContent(20));
    }
    
    @Test
    public void enPassantIsNormallyFalse() {
        assertEquals(false, board.getEnPassantChange());
    }
    
    @Test
    public void enPassantWillBeTrueIfSetTrue() {
        board.setEnPassant(true);
        assertEquals(true, board.getEnPassantChange());
    }
    
    @Test
    public void enPassantWillGoFalseAfterEveryMove() {
        board.setEnPassant(true);
        board.attemptToPlacePiece(a, 20);
        board.attemptToMovePiece(20, 40);
        assertEquals(false, board.getEnPassantChange());
    }
    
    @Test
    public void chessPieceColorWhiteIsToldCorrectly() {
        a = 1;
        b = 12;
        assertEquals(WHITE, board.pieceColor(a));
        assertEquals(WHITE, board.pieceColor(b));
    }
    
    @Test
    public void chessPieceColorBlackIsToldCorrectly() {
        a = 13;
        b = 24;
        assertEquals(BLACK, board.pieceColor(a));
        assertEquals(BLACK, board.pieceColor(b));
    }
    
    @Test
    public void chessPieceColorYellowIsToldCorreclty() {
        a = 0;
        b = 25;
        char c = 300;
        assertEquals(YELLOW, board.pieceColor(a));
        assertEquals(YELLOW, board.pieceColor(b));
        assertEquals(YELLOW, board.pieceColor(c));
    }
    
    @Test
    public void oddChessPiecesAreMarkedAsUnMoved() {
        a = 3;
        board.attemptToPlacePiece(a, 2);
        assertEquals(false, board.hasPieceMovedInSquare(2));
    }
    
    @Test
    public void whenChessPieceMovesItWillTurnToMoved() {
        a = 3;
        board.attemptToPlacePiece(a, 2);
        board.attemptToMovePiece(2, 4);
        assertEquals(true, board.hasPieceMovedInSquare(4));
    }
}
