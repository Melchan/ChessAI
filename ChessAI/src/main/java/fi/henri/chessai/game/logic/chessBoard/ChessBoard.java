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
        if (board[64] == 0) {
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
     * method can only place pieces on board. Pieces are 1-24 see documentation
     * for more info.
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
        return 0 < p && p < 25;
    }

    /**
     * Will tell if coordinateIndex is on chess board.
     *
     * @param i
     * @return true if coordinateIndex is on board.
     */
    public boolean allowedCoordinateIndex(int i) {
        return 0 <= i && i < 64;
    }

    /**
     * Moves chess piece to other square as long as it is on board. Then it sets
     * pieces status to "moved". And enPassant indicator board[65] to 0. After
     * everyMove turn is changed.
     *
     * @param actor
     * @param target
     * @return false if starting square is empty or there is coordinante that is
     * not on board.
     */
    public boolean attemptToMovePiece(int actor, int target) {
        if (allowedCoordinateIndex(actor)) {
            char piece = board[actor];
            if (piece != 0 && allowedPiece(piece)) {
                if (getTurn() == pieceColor(piece)) {
                    saveCurrentBoardPositionToHistory();
                    attemptToPlacePiece(piece, target);
                    setPieceToMoved(target);
                    attemptToEmptySquare(actor);
                    setEnPassant(0);
                    changeTurn();
                    return true;
                }
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
    /**
     * Method switches turns between white and black.
     */
    public void changeTurn() {
        if (board[64] == 0) {
            board[64] = 1;
        } else {
            board[64] = 0;
        }
    }

    /**
     * Piece int chosen location is changed to status moved.
     *
     * @param i
     */
    public void setPieceToMoved(int i) {
        if (this.allowedPiece(board[i])) {
            if (this.board[i] % 2 == 1) {
                this.board[i]++;
            }
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
        if (piece == 0) {
            return YELLOW;
        } else if (piece < 13) {
            return WHITE;
        } else if (piece < 25) {
            return BLACK;
        } else {
            return YELLOW;
        }
    }

    /**
     * method will set enPassantPossible to certain location. It is set by
     * PawnRules Class when after march move it detects it can be eaten with en
     * passant.
     */
    public void setEnPassant(int location) {
        board[65] = (char) location;
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
        if (lenght >= n) {
            this.board = this.boardHistory.get(lenght - n).toCharArray();
            eraseHistory(n);
            return true;
        }
        return false;
    }

    private void eraseHistory(int n) {
        for (int i = 0; i < n; i++) {
            this.boardHistory.remove(boardHistory.size() - 1);
        }
    }

    /**
     * will return enum translation of the ChessPieces characters.
     *
     * @param c
     * @return enum ChessPiece
     */
    public ChessPiece boardCharToChessPiece(char c) {
        return translator.translate(c);
    }

    /**
     * Method will tell what is in square.
     *
     * @param n
     * @return 6000 if given coordinate is not on board;
     */
    public char getSquareContent(int n) {
        if (this.allowedCoordinateIndex(n)) {
            return this.board[n];
        }
        return 6000;
    }

    /**
     * Method will return 0 if there is no en passant change this turn. If there
     * is en passant change this turn. Method will return location of the pawn
     * that marched last turn.
     *
     * @return 0 if no en passant change
     */
    public int getEnPassantChange() {
        return board[65];
    }

    /**
     * method will tell if chessPiece has moved to this index.
     *
     * @param i index which is to be inspected.
     * @return true if piece has moved during game.
     */
    public boolean hasPieceMovedInSquare(int i) {
        if (board[i] % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}