/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import fi.henri.chessai.game.logic.chessBoard.ChessBoardInitializer;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author manhenri
 */
public class LogicHandler {

    private ChessBoard board;
    private ChessBoardInitializer initializer;
    private MoveHandler handler;
    private CheckMateObserver observer;
    private ArrayList<Integer> threatenersForUser;

    private boolean checkMate;
    private boolean draw;

    public LogicHandler(ChessBoard board) {
        this();
        this.board = board;
    }

    public LogicHandler() {
        this.board = new ChessBoard();
        this.initializer = new ChessBoardInitializer(board);
        this.handler = new MoveHandler(board);
        this.observer = new CheckMateObserver(board);
        this.checkMate = false;
        this.draw = false;
        this.initializer.newGame();
    }

    /**
     * Method will put everything on starting position.
     */
    public void newGame() {
        this.checkMate = false;
        this.draw = false;
        this.initializer.newGame();
    }

    /**
     * Method will move chess piece if it is legal. After move it will check if
     * game has proceeded to draw or checkMate
     *
     * @param actor
     * @param target
     */
    public void movePiece(int actor, int target) {
        if (checkMate == false) {
            if (handler.movePiece(actor, target)) {
                validateCheckMate();
                validateDraw();
            }
            this.updateThreateners();
        }
    }

    private void validateCheckMate() {
        this.checkMate = observer.isCheckMate();
    }
    
    public boolean getCheckMate() {
        return checkMate;
    }

    public ArrayList<Integer> getKingThreateners() {
        return this.threatenersForUser;
    }
    
    private void updateThreateners() {
        this.threatenersForUser = handler.getTheateners();
    }
    
    public ChessBoard getChessBoard() {
        return board;
    }
    
    public Color getTurn() {
        return board.getTurn();
    }

    private void validateDraw() {
        //TODO add draw check here when it is ready.
    }
}
