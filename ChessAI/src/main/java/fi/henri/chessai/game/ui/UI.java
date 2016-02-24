/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.ui;

import fi.henri.chessai.game.AI.AI;
import fi.henri.chessai.game.logic.LogicHandler;
import java.awt.Container;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Main Class for UI components.
 *
 * @author manhenri
 */
public class UI implements Runnable {

    private LogicHandler handler;
    private AI ai;

    public UI(LogicHandler handler, AI ai) {
        this.handler = handler;
        this.ai = ai;
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("ChessGame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Container contentPane = new ChessBoardContent(handler, ai);
        frame.setContentPane(contentPane);
        frame.addMouseListener((MouseListener) contentPane);

        frame.pack();
        frame.setVisible(true);
    }
}