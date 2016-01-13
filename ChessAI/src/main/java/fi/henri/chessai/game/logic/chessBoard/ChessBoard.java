/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.chessBoard;

import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import java.util.ArrayList;

/**
 * Core class for everything. Has current board position and board position
 * history.
 *
 * @author manhenri
 */
public class ChessBoard {

    private char[] board;
    private ArrayList<String> boardHistory;
    private ChessBoardTranslator translator;

    public ChessBoard() {
        initialize();
    }

    //TODO CLONE METHOD
    private ChessBoard(char[] board, ArrayList<String> moveHistory) {
        this.board = board.clone();
        this.boardHistory = (ArrayList<String>) moveHistory.clone();
        this.translator = new ChessBoardTranslator();
    }

    // ABOVE ^
    /**
     * method will empty board and history.
     */
    public void initialize() {
        this.board = new char[66];
        this.boardHistory = new ArrayList<String>();
        this.translator = new ChessBoardTranslator();
    }

    public char[] getBoard() {
        return this.board;
    }

    /**
     * method will give tell who has turn the turn to move.
     *
     * @return Color BLACK or WHITE
     */
    public Color getTurn() {
        if (board[64] % 2 == 0) {
            return WHITE;
        } else {
            return BLACK;
        }
    }

    /**
     * Method turns char[] index to x,y coordinates.
     *
     * @param i
     * @return int[0] = x , int [1] = y
     */
    public int[] indexToCoordinates(int i) {
        int[] coordinates = new int[2];
        coordinates[0] = i % 8;
        coordinates[1] = 7 - i / 8;
        return coordinates;
    }

    /**
     * Method will turn coordinates to index.
     *
     * @param c
     * @return corresponding index from char[] chessboard
     */
    public int coordinatesToIndex(int[] c) {
        return -((c[1] - 7) * 8) + c[0];
    }
    
    /**
     * method can place "empty" piece or chess piece in square. pieces are 1-24
     * see documentation for more info.
     *
     * @param p
     * @param i
     * @return true if action is made
     */
    public boolean attemptToPlacePiece(char p, int i) {
        if (allowedPiece(p)) {
            if (allowedCoordinateIndex(i)) {
                this.board[i] = p;
                return true;
            }
        }
        return false;
    }

    private boolean allowedPiece(char p) {
        return 0 < p && p > 65;
    }

    private boolean allowedCoordinateIndex(int i) {
        return 0 <= i && i < 64;
    }

    /**
     * Moves chess piece to other square as long as it is on board. Then it sets
     * pieces status to "moved". And enPassant indicator board[65] to 0. After
     * everyMove turn is changed.
     *
     * @param actor
     * @param target
     * @return true if action is made
     */
    public boolean attemptToMovePiece(int actor, int target) {
        char piece = board[actor];
        if (allowedCoordinateIndex(actor)) {
            if (piece != 0 && allowedPiece(piece)) {
                saveCurrentBoardPositionToHistory();
                attemptToPlacePiece(piece, target);
                setPieceToMoved(target);
                attemptToEmptySquare(actor);
                setEnPassant(false);
                changeTurn();
                return true;

            }
        }
        return false;
    }

    private void saveCurrentBoardPositionToHistory() {
        this.boardHistory.add(new String(board));
    }

    /**
     * Will make square empty if it's on board.
     *
     * @param target
     * @return will return true if operation was commited.
     */
    public boolean attemptToEmptySquare(int target) {
        if (this.allowedCoordinateIndex(target)) {
            board[target] = 0;
            return true;
        }
        return false;
    }

    private void changeTurn() {
        if (board[64] == 0) {
            board[64] = 1;
        } else {
            board[64] = 0;
        }
    }

    private void setPieceToMoved(int i) {
        if (this.board[i] % 2 == 1) {
            this.board[i]++;
        }
    }

    /**
     * method will return white or black if char p is chess piece. If it is not
     * chess piece it will return YELLOW.
     *
     * @param piece
     * @return Color of chess piece
     */
    public Color pieceColor(char piece) {
        if (0 < piece && piece < 13) {
            return WHITE;
        } else if (piece < 25) {
            return BLACK;
        } else {
            return YELLOW;
        }
    }

    /**
     * method will set enPassantPossible to parameter. This method exists for
     * draw of threefold repetition.
     */
    public void setEnPassant(boolean indicator) {
        if (indicator) {
            board[65] = 1;
        } else {
            board[65] = 0;
        }
    }

    /**
     * Method turns clock backwards and replaces current board position with
     * earlier one. At the same time it will erase earlier moves from memory.
     * 
     * @param n
     * @return false if trying to revert more than possible.
     */
    public boolean rollBack(int n) {
        int lenght = boardHistory.size();
        if (lenght < n) {
            this.board = this.boardHistory.get(lenght - n).toCharArray();
            eraseHistory(n);
            return true;
        }
        return false;
    }
    
    private void eraseHistory(int n) {
        for (int i = 1; i <= n ; i++) {
            this.boardHistory.remove(boardHistory.size() - i);
        }
    }
    
    /**
     * will return enum translation of the ChessPieces characters.
     * @param c
     * @return enum ChessPiece
     */
    public ChessPiece boardCharToChessPiece(char c) {
        return translator.translate(c);
    }
}
