/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game;

import fi.henri.chessai.game.AI.AI;
import fi.henri.chessai.game.logic.LogicHandler;
import fi.henri.chessai.game.ui.UI;

/**
 *
 * @author manhenri
 */
public class Game {
    private LogicHandler handler;
    private UI ui;
    private AI ai;
    
    public Game() {
        this.handler = new LogicHandler();
        this.ai = new AI(handler);
        this.ui = new UI(handler);
    }
    
    public void run() {
        ui.run();
    }
}
