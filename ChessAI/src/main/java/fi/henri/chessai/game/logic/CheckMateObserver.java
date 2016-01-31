/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import java.util.ArrayList;

/**
 *
 * @author manhenri
 */
public class CheckMateObserver {

    private ChessBoard board;
    private MoveHandler handler;

    public CheckMateObserver(ChessBoard board) {
        this.board = board;
        this.handler = new MoveHandler(board);
    }

    public boolean isCheckMate() {
        if (kingIsInCheck()) {
            int king = handler.getThreatenedKingLocation();
            ArrayList<Integer> defenders = handler.getDefenders();
            int threatCount = handler.getTheateners().size();
            int attacker = handler.getTheateners().get(0);
            if (kingCanNotMoveToSafety(king)) {
                if (threatCount > 1) {
                    return true;
                } else if (enemyCanBeEaten(attacker, defenders)) {
                    return false;
                } else {
                    return enemyCanNotBeBlocked(king, attacker, defenders);
                }
            }
        }
        return false;
    }

    private boolean kingIsInCheck() {
        board.changeTurn();
        if (handler.isKingThreatened()) {
            board.changeTurn();
            return true;
        }
        board.changeTurn();
        return false;
    }

    private boolean kingCanNotMoveToSafety(int king) {
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                int[] k = board.indexToCoordinates(king);
                k[0] += x;
                k[1] += y;
                int target = board.coordinatesToIndex(k);
                if (handler.movePiece(king, target)) {
                    board.rollBack(1);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean enemyCanBeEaten(int attacker, ArrayList<Integer> defenders) {
        for (int d : defenders) {
            if (handler.movePiece(d, attacker)) {
                board.rollBack(1);
                return true;
            }
        }
        return false;
    }

    private boolean enemyCanNotBeBlocked(int king, int attacker, ArrayList<Integer> defenders) {

        int[] k = board.indexToCoordinates(king);
        int[] a = board.indexToCoordinates(attacker);

        while (k[0] != a[0] || k[1] != a[1]) {
            int i = board.coordinatesToIndex(k);
            for (int d : defenders) {
                if (handler.movePiece(d, i)) {
                    board.rollBack(1);
                    return false;
                }
            }
            k[0] = changeToOneCloserToTarget(k[0], a[0]);
            k[1] = changeToOneCloserToTarget(k[1], a[1]);
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
}