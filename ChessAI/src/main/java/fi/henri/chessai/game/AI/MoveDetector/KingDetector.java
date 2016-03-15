/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI.MoveDetector;

import fi.henri.chessai.game.logic.LogicHandler;
import fi.henri.chessai.game.dataStructure.ArrayList;


/**
 *
 * @author manhenri
 */
class KingDetector extends MoveDetector {

    public KingDetector(LogicHandler handler) {
        super(handler);
    }

    @Override
    public ArrayList<String> possibleMoves(int location) {
        return kingMoves(location);
    }

    private ArrayList<String> kingMoves(int i) {
        ArrayList<String> result = new ArrayList<String>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                int[] k = super.board.indexToCoordinates(i);
                k[0] += x;
                k[1] += y;
                int target = board.coordinatesToIndex(k);
                super.tryToMoveAndRecord(i, target, result);
            }
        }
        result.addAll(possibleCastlingMove(i));
        return result;
    }

    private ArrayList<String> possibleCastlingMove(int i) {
        ArrayList<String> result = new ArrayList<String>();
        String s = "";
        if (!super.board.hasPieceMovedInSquare(i)) {
            int[] a = super.board.indexToCoordinates(i);
            a[0] += 2;
            int target = board.coordinatesToIndex(a);
            if (super.moveHandler.movePiece(i, target)) {
                s += (super.rightSize(i) + super.rightSize(target));
                result.add(s);
                super.handler.rollBack(1);
            }
            s = "";
            a[0] -= 4;
            target = board.coordinatesToIndex(a);
            if (super.moveHandler.movePiece(i, target)) {
                s += (super.rightSize(i) + super.rightSize(target));
                result.add(s);
                super.handler.rollBack(1);
            }

        }
        return result;
    }
}
