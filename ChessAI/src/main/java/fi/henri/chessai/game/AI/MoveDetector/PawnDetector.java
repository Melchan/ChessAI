/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI.MoveDetector;

import fi.henri.chessai.game.logic.LogicHandler;
import java.util.ArrayList;

/**
 *
 * @author manhenri
 */
class PawnDetector extends MoveDetector {

    public PawnDetector(LogicHandler handler) {
        super(handler);
    }

    @Override
    public ArrayList<String> possibleMoves(int location) {
        return pawnMoves(location);
    }

    private ArrayList<String> pawnMoves(int i) {
        ArrayList<String> result = new ArrayList<String>();
        for (int x = -1; x < 2; x++) {
            String s = "";
            for (int y = -1; y < 2; y++) {
                int[] k = super.board.indexToCoordinates(i);
                k[0] += x;
                k[1] += y;
                int target = board.coordinatesToIndex(k);
                if (super.handler.movePiece(i, target)) {
                    s += (super.rightSize(i) + super.rightSize(target));
                    result.add(s);
                    super.board.rollBack(1);
                }
            }
        }
        result.addAll(possibleMarchMove(i));
        return result;
    }

    private ArrayList<String> possibleMarchMove(int i) {
        ArrayList<String> result = new ArrayList<String>();
        String s = "";
        if (super.board.hasPieceMovedInSquare(i)) {
            int[] a = super.board.indexToCoordinates(i);
            a[1] += 2;
            int target = board.coordinatesToIndex(a);
            if (super.handler.movePiece(i, target)) {
                s += (super.rightSize(i) + super.rightSize(target));
                result.add(s);
                super.handler.rollBack(1);
            } else {
                a[1] -= 4;
                target = board.coordinatesToIndex(a);
                if (super.handler.movePiece(i, target)) {
                    s += (super.rightSize(i) + super.rightSize(target));
                    result.add(s);
                    super.handler.rollBack(1);
                }
            }
        }
        return result;
    }
}
