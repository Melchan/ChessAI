/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.moves;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;

/**
 *
 * @author manhenri
 */
public abstract class PieceMovement {

    private ChessBoard board;

    public PieceMovement(ChessBoard board) {
        this.board = board;
    }

    /**
     * method will commit action for chesspiece if move is legal. Will not
     * notice if king is threatened.
     *
     * @param actor
     * @param target
     * @return true if chesspiece has moved
     */
    public boolean movePiece(int actor, int target) {
        if (this.allowedParameters(actor, target)) {
            System.out.println("not allowed parameter");
            if (this.isPieceEnemy(actor, target)) {
                return commitIfMoveIsLegal(actor, target);
            }
        }
        return false;
    }

    /**
     * method will commit move for chess piece if it is legal.
     *
     * @param actor
     * @param target
     * @return
     */
    protected abstract boolean commitIfMoveIsLegal(int actor, int target);

    /**
     * Method will check if target square hosts an enemy.
     *
     * @param actor
     * @param target
     * @return false if target is friend
     */
    private boolean isPieceEnemy(int actor, int target) {
        char a = board.getSquareContent(actor);
        char t = board.getSquareContent(target);
        System.out.println(board.pieceColor(a) + " " + board.pieceColor(t));

        return board.pieceColor(a) != board.pieceColor(t);
    }

    /**
     * method will check if parameters are not same or outside board.
     *
     * @param actor
     * @param target
     * @return true if parameters are allowed
     */
    private boolean allowedParameters(int actor, int target) {
        if (board.allowedCoordinateIndex(actor)) {
            if (board.allowedCoordinateIndex(target)) {
                return actor != target;
            }
        }
        return false;
    }

    /**
     * Will give reference to ChessBoard in super.
     *
     * @return chessBoard in super.
     */
    protected ChessBoard getBoard() {
        return this.board;
    }

    /**
     * Will tell if there is no pieces between two points
     *
     * @param actor
     * @param target
     * @return false if there is something blocking the way.
     */
    protected boolean pathClear(int actor, int target) {
        int[] a = board.indexToCoordinates(actor);
        int[] t = board.indexToCoordinates(target);
        a[0] = changeToOneCloserToTarget(a[0], t[0]);
        a[1] = changeToOneCloserToTarget(a[1], t[1]);

        while (a[0] != t[0] || a[1] != t[1]) {
            int i = board.coordinatesToIndex(a);
            if (someoneIsBlocking(i)) {
                return false;
            }
            a[0] = changeToOneCloserToTarget(a[0], t[0]);
            a[1] = changeToOneCloserToTarget(a[1], t[1]);
        }
        return true;
    }

    private int changeToOneCloserToTarget(int a, int t) {
        if (a != t) {
            if (a < t) {
                a++;
                return a;
            } else {
                a--;
                return a;
            }
        }
        return a;
    }
    
    private boolean someoneIsBlocking(int target) {
        if (board.getSquareContent(target) != 0) {
            return true;
        }
        return false;
    }
}