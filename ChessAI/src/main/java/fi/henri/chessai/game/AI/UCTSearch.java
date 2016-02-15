/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI;

import fi.henri.chessai.game.logic.LogicHandler;
import java.awt.Color;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author manhenri
 */
public class UCTSearch {
    private LogicHandler handler;
    private final int win = 4186;
    private final int draw = 0;
    private final int lose = -4186;
    private int explorationConstant = 8;
    private HashMap<String, Integer> memory;
    private HashMap<String, Node> tree;
    private Color player;
    
    public UCTSearch(LogicHandler handler, Color player) {
        this.handler = handler;
        this.memory = new HashMap<>();
        this.tree = new HashMap<>();
        this.player = player;
    }
    
    public void commitMove() {
        int time = 100;
        while (time > 0) {
            simulate();
        }
        selectMove();
    }

    private void simulate() {
        simTree();
    }

    private void selectMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void simTree() {
        int c = explorationConstant;
        int t = 0;
        while (notGameOver()) {
            String key = new String(handler.getChessBoard().getBoard());
            if (tree.containsKey(key)) {
                tree.put(key, new Node(handler));
            }
            selectMove();
        }
    }

    private boolean notGameOver() {
        return handler.getCheckMate() || handler.getDraw();
    }
}
