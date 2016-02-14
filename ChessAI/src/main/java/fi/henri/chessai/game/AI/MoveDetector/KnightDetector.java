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
class KnightDetector extends MoveDetector {
    private ArrayList<Integer> helpX;
    private ArrayList<Integer> helpY;
    
    public KnightDetector(LogicHandler handler) {
        super(handler);
        this.helpX = new ArrayList<>();
        this.helpY = new ArrayList<>();
        initializeHelp();
    }

    @Override
    public ArrayList<String> possibleMoves(int location) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < 8; i++) {
            int[] t = board.indexToCoordinates(location);
            t[0] += helpX.get(i);
            t[1] += helpY.get(i);
            int target = super.board.coordinatesToIndex(t);
            super.tryToMoveAndRecord(location, target, result);
        }
        
        return result;
    }
    
    //if time get something fancier.
    private void initializeHelp() {
        helpX.add(-1);
        helpY.add(-2);
        
        helpX.add(-2);
        helpY.add(-1);
        
        helpX.add(-2);
        helpY.add(1);
        
        helpX.add(-1);
        helpY.add(2);
        
        helpX.add(1);
        helpY.add(2);
        
        helpX.add(2);
        helpY.add(1);
        
        helpX.add(2);
        helpY.add(-1);
        
        helpX.add(1);
        helpY.add(-2);
    }
}