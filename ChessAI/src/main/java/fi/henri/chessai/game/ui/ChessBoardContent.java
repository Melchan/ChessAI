/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.ui;

import fi.henri.chessai.game.AI.AI;
import fi.henri.chessai.game.logic.LogicHandler;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Main component where every chessboard square is placed in gridlayout.
 *
 * @author manhenri
 */
public class ChessBoardContent extends JPanel implements Updatetable, MouseListener {

    private ArrayList<SquarePanel> squares;
    private ArrayList<Integer> kingThreateners;
    private Integer firstPaneNumber;
    private Integer secondPaneNumber;
    private LogicHandler handler;
    private AI ai;

    public ChessBoardContent(LogicHandler handler, AI ai) {
        this.handler = handler;
        this.ai = ai;
        this.squares = new ArrayList();
        this.kingThreateners = new ArrayList<>();
        this.setLayout(new GridLayout(8, 8));
        this.setPreferredSize(new Dimension(640, 640));
        createSquaresInArrayList();
        for (SquarePanel sp : squares) {
            this.add(sp);
            this.addMouseListener(sp);
        }
    }

    private void createSquaresInArrayList() {
        for (int i = 0; i < 64; i++) {
            squares.add(new SquarePanel(handler, i, this));
        }
    }

    public Integer getFirstPane() {
        return this.firstPaneNumber;
    }
    
    @Override
    public void update(int paneNumber) {
        if (firstPaneNumber == null) {
            firstPaneNumber = paneNumber;
            this.kingThreateners = null;
        } else {
            secondPaneNumber = paneNumber;
            handleLogicAction();
            firstPaneNumber = null;
            secondPaneNumber = null;
            updateKingThreateners();
        }
        refresh();
    }

    private void updateKingThreateners() {
        this.kingThreateners = handler.getKingThreateners();
    }

    public ArrayList<Integer> getKingThreateners() {
        return kingThreateners;
    }

    private void refresh() {
        if (handler.getCheckMate()) {
            setWinningPanel();
        } else if (handler.getDraw()) {
            setDrawPanel();
        }
        this.revalidate();
        this.repaint();

    }
    
    private void setWinningPanel() {
        this.removeAll();
        this.setLayout(new GridLayout(1, 1));
        this.setPreferredSize(new Dimension(100, 80));
        JLabel label = new JLabel();
        label.setText(label.getText()+ handler.getTurn() + " WON!");
        this.add(label);
    }
    
    private void setDrawPanel() {
        this.removeAll();
        this.setLayout(new GridLayout(1, 1));
        this.setPreferredSize(new Dimension(100, 80));
        JLabel label = new JLabel();
        label.setText(label.getText() + " DRAW!");
        this.add(label);
    }

    private void handleLogicAction() {
        if (handler.movePiece(firstPaneNumber, secondPaneNumber)) {
            ai.movePiece();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}