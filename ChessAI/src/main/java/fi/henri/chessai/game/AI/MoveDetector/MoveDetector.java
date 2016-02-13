/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI.MoveDetector;

import fi.henri.chessai.game.logic.LogicHandler;
import fi.henri.chessai.game.logic.MoveHandler;
import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.NOTCHESSPIECE;
import java.util.ArrayList;

/**
 *
 * @author manhenri
 */
public abstract class MoveDetector {

    protected LogicHandler handler;
    protected ChessBoard board;
    protected MoveHandler moveHandler;

    public MoveDetector(LogicHandler handler) {
        this.handler = handler;
        this.board = handler.getChessBoard();
        this.moveHandler = handler.getMoveHandler();
        
    }

    /**
     * Will return all possible moves for chesspiece in question
     *
     *
     * @param location of piece
     * @return List of strings where two under hundred integers are in string
     * format example 0401 or 6300 or 4321
     */
    public abstract ArrayList<String> possibleMoves(int location);

    /**
     * Will give out how much x and y axis will change between moves.
     *
     * @param actor
     * @param target
     * @return result[0] = xChange result[1] = yChange
     */
    protected int[] differenceBetweenTwoPoints(int actor, int target) {
        int[] result = new int[2];
        int[] a = board.indexToCoordinates(actor);
        int[] t = board.indexToCoordinates(target);

        result[0] = Math.abs(a[0] - t[0]);
        result[1] = Math.abs(a[1] - t[1]);

        return result;
    }

    /**
     * Will try to move in every square between two points for as long as it
     * encounters move that is not legal
     *
     * @param actor
     * @param target
     * @return returns all possible places to move with this piece between
     * points.
     */
    protected ArrayList<String> pathToMove(int actor, int target) {
        ArrayList<String> result = new ArrayList<String>();
        int[] a = board.indexToCoordinates(actor);
        int[] t = board.indexToCoordinates(target);
        a[0] = changeToOneCloserToTarget(a[0], t[0]);
        a[1] = changeToOneCloserToTarget(a[1], t[1]);
        int newTarget = board.coordinatesToIndex(a);
        
        while (a[0] != t[0] || a[1] != t[1]) {
            tryToMoveAndRecord(actor, newTarget, result);
            
            a[0] = changeToOneCloserToTarget(a[0], t[0]);
            a[1] = changeToOneCloserToTarget(a[1], t[1]);
            
            newTarget = board.coordinatesToIndex(a);
            char piece = board.getSquareContent(newTarget);
            if (!(board.boardCharToChessPiece(piece) == NOTCHESSPIECE)) {
                tryToMoveAndRecord(actor, newTarget, result);
                break;
            }
        }
        if (moveHandler.movePiece(actor, target)) {
            result.add("" + rightSize(actor) + rightSize(target));
            handler.rollBack(1);
        }
        return result;
    }
    
    protected void tryToMoveAndRecord(int actor, int target, ArrayList<String> result) {
        if (moveHandler.movePiece(actor, target)) {
                result.add("" + rightSize(actor) + rightSize(target));
                handler.rollBack(1);
            }
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

    /**
     * turns integer to right format for saving in string
     *
     * @param i
     * @return returns size 2 string.
     */
    protected String rightSize(int i) {
        String result = "" + i;
        if (result.length() < 2) {
            result = "0" + i;
        }
        return result;
    }
}