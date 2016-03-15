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
class QueenDetector extends MoveDetector {
    private RookDetector rook;
    private BishopDetector bishop;

    public QueenDetector(LogicHandler handler) {
        super(handler);
        this.bishop = new BishopDetector(handler);
        this.rook = new RookDetector(handler);
        
    }

    @Override
    public ArrayList<String> possibleMoves(int location) {
        ArrayList<String> result = new ArrayList<>();
        result.addAll(rook.possibleMoves(location));
        result.addAll(bishop.possibleMoves(location));
        return result;
    }
}
