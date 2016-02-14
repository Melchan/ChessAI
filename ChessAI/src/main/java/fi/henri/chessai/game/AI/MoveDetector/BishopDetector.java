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
        ArrayList<String> result = new ArrayList<String>();
        
        for (int t : endPoints(location)) {
            result.addAll(super.pathToMove(location, t));
        }
        
        return result;
    }
    
    private ArrayList<Integer> endPoints(int location) {
        ArrayList<Integer> result = new ArrayList<>();
        int[] t = super.board.indexToCoordinates(location);
        int left = t[0] + 0;
        int right = 7 - t[0];
        int down = t[1] + 0;
        int up = 7 - t[1];
        
        result.add(leftDown(left, down, t));
        
        t = super.board.indexToCoordinates(location);
        result.add(leftUp(left, up, t));
        
        t = super.board.indexToCoordinates(location);
        result.add(rightUp(right, up, t));
        
        t = super.board.indexToCoordinates(location);
        result.add(rightDown(right, down, t));
        
        return result;
    }
    
    private int leftDown(int left, int down, int[] coordinate) {
        int change = Math.min(left, down);
        coordinate[0] -= change;
        coordinate[1] -= change;
        return super.board.coordinatesToIndex(coordinate);
    }
    
    private int leftUp(int left, int up, int[] coordinate) {
        int change = Math.min(left, up);
        coordinate[0] -= change;
        coordinate[1] += change;
        return super.board.coordinatesToIndex(coordinate);
    }
    
    private int rightUp(int right, int up, int[] coordinate) {
        int change = Math.min(right, up);
        coordinate[0] += change;
        coordinate[1] += change;
        return super.board.coordinatesToIndex(coordinate);
    }
    
    private int rightDown(int right, int down, int[] coordinate) {
        int change = Math.min(right, down);
        coordinate[0] += change;
        coordinate[1] -= change;
        return super.board.coordinatesToIndex(coordinate);
    }
}