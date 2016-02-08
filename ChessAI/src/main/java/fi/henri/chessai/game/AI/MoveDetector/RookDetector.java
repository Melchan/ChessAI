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
class RookDetector extends MoveDetector {

    public RookDetector(LogicHandler handler) {
        super(handler);
    }

    @Override
    public ArrayList<String> possibleMoves(int location) {
        return rookMoves(location);
    }
    
    //no idea how to prevent copy-paste code without it getting even uglier
    private ArrayList<Integer> moveDirections(int i) {
        ArrayList<Integer> result = new ArrayList<>();
        
        int[] a = super.board.indexToCoordinates(i);
        a[1] = 0;
        int t = super.board.coordinatesToIndex(a);
        result.add(t);
        a[1] = 7;
        t = super.board.coordinatesToIndex(a);
        result.add(t);
        
        a = super.board.indexToCoordinates(i);
        a[0] = 0;
        t = super.board.coordinatesToIndex(a);
        result.add(t);
        a[0] = 7;
        t = super.board.coordinatesToIndex(a);
        result.add(t);
        
        return result;
    }
    
    private ArrayList<String> rookMoves(int i) {
        ArrayList<Integer> endPoints = moveDirections(i); 
        ArrayList<String> result = new ArrayList<String>();
        for (int l : endPoints) {
            result.addAll(super.pathToMove(i, l));
        }
        return result;
    }
}