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
class BishopDetector extends MoveDetector {

    public BishopDetector(LogicHandler handler) {
        super(handler);
    }

    @Override
    public ArrayList<String> possibleMoves(int location) {
        return bishopMoves(location);
    }

    private ArrayList<String> bishopMoves(int location) {
        ArrayList<String> result = new ArrayList<String>();
        
        for (int t : endPoints(location)) {
            super.tryToMoveAndRecord(t, t, result);
        }
        
        return result;
    }
    
    private ArrayList<Integer> endPoints(int location) {
        ArrayList<Integer> result = new ArrayList<>();
        int t[] = super.board.indexToCoordinates(location);
        int left = Math.abs(t[0] - 7);
        int right = Math.abs(7 - t[0]);
        
        t[0] -= left;
        t[1] -= left;
        return result;
    }
}
