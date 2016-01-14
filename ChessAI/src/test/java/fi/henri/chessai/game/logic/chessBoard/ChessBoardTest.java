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
    private char w;
    private char b;

    @Before
    public void setUp() {
        this.board = new ChessBoard();
        this.w = 4;
        this.b = 20;
    }

    @Test
    public void whiteHasFirstTurn() {
        assertEquals(WHITE, board.getTurn());
    }

    @Test
    public void chessPieceCanOnlyBePlacedOnBoard() {
        w = 2;
        assertEquals(false, board.attemptToPlacePiece(w, 64));
        assertEquals(false, board.attemptToPlacePiece(w, -1));
    }

    @Test
    public void onlyChessPiecesCanBePlacedOnBoard() {
        b = 0;
        assertEquals(false, board.attemptToPlacePiece(b, 4));
        w = 25;
        assertEquals(false, board.attemptToPlacePiece(w, 4));
    }

    @Test
    public void canPlaceChessPieceOnBoard() {
        b = 1;
        assertEquals(true, board.attemptToPlacePiece(b, 4));
        w = 24;
        assertEquals(true, board.attemptToPlacePiece(w, 4));
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
        board.attemptToPlacePiece(w, 4);
        board.attemptToMovePiece(4, 20);
        assertEquals(w, board.getSquareContent(20));
    }

    @Test
    public void whenPieceIsMovedSquareItLeftIsEmptiet() {
        b = 0;
        board.attemptToPlacePiece(w, 4);
        board.attemptToMovePiece(4, 20);
        assertEquals(b, board.getSquareContent(4));
    }

    @Test
    public void whenMoveIsDoneTurnChangesAccordingly() {
        board.attemptToPlacePiece(w, 4);
        board.attemptToPlacePiece(b, 22);
        board.attemptToMovePiece(4, 20);
        assertEquals(BLACK, board.getTurn());
        board.attemptToMovePiece(22, 41);
        assertEquals(WHITE, board.getTurn());
    }

    @Test
    public void rollBackWorks() {
        board.attemptToPlacePiece(w, 4);
        board.attemptToMovePiece(4, 20);
        board.rollBack(1);
        assertEquals(w, board.getSquareContent(4));
    }

    @Test
    public void cantRollBackMoreThanThereHasBeenTurns() {
        board.attemptToPlacePiece(w, 4);
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
        board.attemptToPlacePiece(w, 4);
        board.attemptToPlacePiece(b, 8);
        board.attemptToMovePiece(4, 20); //white
        board.attemptToMovePiece(8, 14); //black
        
        board.attemptToMovePiece(20, 1); //white
        board.attemptToMovePiece(14, 30); //black
        
        board.attemptToMovePiece(1, 21); //white
        board.attemptToMovePiece(30, 40); //black
        board.rollBack(4);
        assertEquals(w, board.getSquareContent(20));
        assertEquals(b, board.getSquareContent(14));
    }

    @Test
    public void rollBackClearsMoveHistory() {
        board.attemptToPlacePiece(w, 4);
        board.attemptToPlacePiece(b, 8);
        board.attemptToMovePiece(4, 20); //white
        board.attemptToMovePiece(8, 14); //black
        
        board.rollBack(1);
        
        board.attemptToMovePiece(8, 1); //black
        
        board.attemptToMovePiece(20, 30); //white
        board.attemptToMovePiece(1, 21); //black
        
        board.rollBack(4);
        
        board.attemptToMovePiece(4, 40); //white
        assertEquals(w, board.getSquareContent(40));
        assertEquals(b, board.getSquareContent(8));
    }

    @Test
    public void indexToCoordinateWorks() {
        char[][] b = new char[8][8];
        for (int i = 0; i < 64; i++) {
            int[] c = board.indexToCoordinates(i);
            b[c[0]][c[1]] = w;
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                assertEquals(w, b[x][y]);
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
                b[i] = w;
            }
        }

        for (int i = 0; i < 64; i++) {
            assertEquals(w, b[i]);
        }
    }

    @Test
    public void attemptToEmptySquareDoesntWorkWithBacCoordinate() {
        b = 65;
        assertEquals(false, board.attemptToEmptySquare(b));
    }

    @Test
    public void attemptToEmptySquareEmptiesSquare() {
        board.attemptToPlacePiece(w, 20);
        board.attemptToEmptySquare(20);
        b = 0;
        assertEquals(b, board.getSquareContent(20));
    }

    @Test
    public void enPassantIsNormallyZero() {
        assertEquals(0, board.getEnPassantChange());
    }

    @Test
    public void enPassantWillBeTrueIfSetTrue() {
        board.setEnPassant(30);
        assertEquals(30, board.getEnPassantChange());
    }

    @Test
    public void enPassantWillGoFalseAfterEveryMove() {
        board.setEnPassant(30);
        board.attemptToPlacePiece(w, 20);
        board.attemptToMovePiece(20, 40);
        assertEquals(0, board.getEnPassantChange());
    }

    @Test
    public void chessPieceColorWhiteIsToldCorrectly() {
        w = 1;
        b = 12;
        assertEquals(WHITE, board.pieceColor(w));
        assertEquals(WHITE, board.pieceColor(b));
    }

    @Test
    public void chessPieceColorBlackIsToldCorrectly() {
        w = 13;
        b = 24;
        assertEquals(BLACK, board.pieceColor(w));
        assertEquals(BLACK, board.pieceColor(b));
    }

    @Test
    public void chessPieceColorYellowIsToldCorreclty() {
        w = 0;
        b = 25;
        char c = 300;
        assertEquals(YELLOW, board.pieceColor(w));
        assertEquals(YELLOW, board.pieceColor(b));
        assertEquals(YELLOW, board.pieceColor(c));
    }

    @Test
    public void oddChessPiecesAreMarkedAsUnMoved() {
        w = 3;
        board.attemptToPlacePiece(w, 2);
        assertEquals(false, board.hasPieceMovedInSquare(2));
    }

    @Test
    public void whenChessPieceMovesItWillTurnToMoved() {
        w = 3;
        board.attemptToPlacePiece(w, 2);
        board.attemptToMovePiece(2, 4);
        assertEquals(true, board.hasPieceMovedInSquare(4));
    }
    
    @Test
    public void chessPieceCanOnlyMoveOnItsOwnTurn() {
        board.attemptToPlacePiece(w, 2);
        board.attemptToPlacePiece(b, 4);
        
        assertEquals(false, board.attemptToMovePiece(4, 8));
        assertEquals(true, board.attemptToMovePiece(2, 14));
        assertEquals(false, board.attemptToMovePiece(14, 12));
        assertEquals(true, board.attemptToMovePiece(4, 9));
        assertEquals(true, board.attemptToMovePiece(14, 22));
    }
}
