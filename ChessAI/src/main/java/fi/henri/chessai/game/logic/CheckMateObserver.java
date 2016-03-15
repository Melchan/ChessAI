/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import fi.henri.chessai.game.dataStructure.ArrayList;

/**
 *
 * @author manhenri
 */
public class CheckMateObserver {

    private ChessBoard board;
    private MoveHandler handler;
    private boolean kingAloneImmobile;

    public CheckMateObserver(ChessBoard board) {
        this.board = board;
        this.handler = new MoveHandler(board);
        this.kingAloneImmobile = false;
    }

    /**
     * will check if game is in checkMate
     *
     * @return
     */
    public boolean isCheckMate() {
        this.kingAloneImmobile = false;
        if (kingIsInCheck()) {
            int king = handler.getThreatenedKingLocation();
            ArrayList<Integer> defenders = handler.getDefenders();
            int threatCount = handler.getTheateners().size();
            int attacker = handler.getTheateners().get(0);

            if (defenders.size() > 16) {
                System.out.println("liar liar pans of fire");
            }

            if (handler.getDefenders().isEmpty() || handler.getTheateners().isEmpty()) {
                System.out.println("ChechMateObserver has trouble");
            }

            if (kingCanNotMoveToSafety(king)) {
                if (threatCount > 1) {
                    return true;
                } else if (enemyCanBeEaten(attacker, defenders)) {
                    return false;
                } else if (enemyCanNotBeBlocked(king, attacker, defenders)) {
                    return true;
                } else {
                    checkIfKingIsAloneAndImmobile(defenders, king);
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
        if (defenders.isEmpty()) {
            System.out.println(board.getMoveCount() + " when defender is empty");
        }
        int size = defenders.size();
        for (int i = 0; i < size; i++) {
            if (handler.movePiece(defenders.get(i), attacker)) {
                board.rollBack(1);
                return true;
            }
        }
        return false;
    }

    private boolean enemyCanNotBeBlocked(int king, int attacker, ArrayList<Integer> defenders) {

        int[] k = board.indexToCoordinates(king);
        int[] a = board.indexToCoordinates(attacker);
        if (defenders.isEmpty()) {
            System.out.println(board.getMoveCount() + " when defender is empty");
        }
        while (k[0] != a[0] || k[1] != a[1]) {
            int i = board.coordinatesToIndex(k);
            int size = defenders.size();
            for (int d = 0; d < size; d++) {
                System.out.println(defenders.get(d) + " " + i);
                if (handler.movePiece(defenders.get(d), i)) {
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

    public boolean kingCannotMove() {
        return this.kingAloneImmobile;
    }

    private void checkIfKingIsAloneAndImmobile(ArrayList<Integer> defenders, int king) {
        if (defenders.size() <= 1) {
            this.kingAloneImmobile = kingCanNotMoveToSafety(king);
        }
    }
}
