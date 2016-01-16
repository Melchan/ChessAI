/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.BLACKKING;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.WHITEKING;
import fi.henri.chessai.game.logic.moves.MoveLibrary;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import java.util.ArrayList;

/**
 *
 * @author manhenri
 */
public class MoveHandler {

    private ChessBoard board;
    private MoveLibrary library;
    private ArrayList<Integer> threateners;
    private int king;
    private ArrayList<Integer> attackers;
    private ArrayList<Integer> defenders;

    public MoveHandler(ChessBoard board) {
        this.board = board;
        this.library = new MoveLibrary(board);
        this.threateners = new ArrayList<Integer>();
        this.attackers = new ArrayList<Integer>();
        this.defenders = new ArrayList<Integer>();
    }

    /**
     * Method tries to move chess piece using movelibrary. If piece is moved.
     * Method will check if moving players king is in check. If it is method
     * will call one move rollback and return false.
     *
     * @param actor
     * @param target
     * @return true if move is commited.
     */
    public boolean movePiece(int actor, int target) {
        boolean moved = board.hasPieceMovedInSquare(actor);
        if (library.movePiece(actor, target)) {
            if (this.isKingThreatened() == false) {
                return checkCastling(moved, actor, target);
            }
            board.rollBack(1);
        }
        return false;
    }

    private boolean checkCastling(boolean moved, int actor, int target) {
        if (!moved && isKing(board.getSquareContent(actor))) {
            if (Math.abs(actor - target) > 1) {
                if (castlingIsClear(actor, target)) {
                    return library.movePiece(actor, target);
                }
            }
        }
        return true;
    }

    private boolean isKing(char p) {
        if (board.boardCharToChessPiece(p) == WHITEKING) {
            return true;
        } else if (board.boardCharToChessPiece(p) == BLACKKING) {
            return true;
        }
        return false;
    }

    /**
     * Method will see if color given in parameters has it's king threatened.
     * Save threateners in arraylist. and return true.
     *
     * @param color
     * @return false if king is not threatened.
     */
    public boolean isKingThreatened() {
        initializeCheckChecker();
        boolean result = false;
        for (int a : attackers) {
            if (library.movePiece(a, king)) {
                board.rollBack(1);
                this.threateners.add(a);
                result = true;
            }
        }
        return result;
    }

    private void initializeCheckChecker() {
        this.threateners.clear();
        this.attackers.clear();
        this.defenders.clear();
        this.king = 100;
        initializeKingAndAttackers();
    }

    private void initializeKingAndAttackers() {
        Color color = getColor();
        for (int i = 0; i < 64; i++) {
            char p = board.getSquareContent(i);
            Color c = board.pieceColor(p);
            if (c != YELLOW) {
                if (c == color) {
                    checkforKing(i, p);
                    this.defenders.add(i);
                } else {
                    this.attackers.add(i);
                }
            }
        }
    }

    private Color getColor() {
        if (board.getTurn() == WHITE) {
            return BLACK;
        } else {
            return WHITE;
        }
    }

    private void checkforKing(int i, char p) {
        if (WHITEKING == board.boardCharToChessPiece(p)) {
            this.king = i;
        } else if (BLACKKING == board.boardCharToChessPiece(p)) {
            this.king = i;
        }
    }

    /**
     *
     * @return indexes of threateners. Noticed last time when isKingThreatened
     * was Called.
     */
    public ArrayList<Integer> getTheateners() {
        return threateners;
    }

    private boolean castlingIsClear(int actor, int target) {
        board.rollBack(1);
        int a = actor;
        while (a != target) {
            board.attemptToMovePiece(actor, target);
            if(isKingThreatened()) {
                board.rollBack(1);
                return true;
            }
            board.rollBack(1);
            bringCloserToTarget(a, target);
        }
        return true;
    }

    private int bringCloserToTarget(int actor, int target) {
        int result;
        if (actor > target) {
            result = actor--;
        } else {
            result = actor++;
        }
        return result;
    }
    /**
     * 
     * @return last king whose safety was confirmed from MoveHandler.
     */
    public int getThreatenedKingLocation() {
        return king;
    }
    /**
     * 
     * @return last defenders whose safety was confirmed from MoveHandler. 
     */
    public ArrayList<Integer> getDefenders() {
        return defenders;
    }
}
