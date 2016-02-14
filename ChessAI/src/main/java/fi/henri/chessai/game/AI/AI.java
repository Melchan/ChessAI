package fi.henri.chessai.game.AI;

import fi.henri.chessai.game.logic.LogicHandler;
import java.awt.Color;

/**
 *
 * @author manhenri
 */
public class AI {
    private LogicHandler handler;
    private Color player;
    
    public AI(LogicHandler logic, Color color) {
        this.handler = logic;
        this.player = color;
    }
    
    public void movePiece() {
        
    }
}
