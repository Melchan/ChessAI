/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI;

import fi.henri.chessai.game.AI.MoveDetector.MoveObserver;
import fi.henri.chessai.game.logic.LogicHandler;
import java.util.ArrayList;

/**
 *
 * @author manhenri
 */
public class Node {
    private LogicHandler handler;
    private ArrayList<String> moves;
    
    public Node(LogicHandler handler) {
        this.handler = handler;
        this.moves = new MoveObserver(handler).getPossibleMoves();
    }
    
    
}
