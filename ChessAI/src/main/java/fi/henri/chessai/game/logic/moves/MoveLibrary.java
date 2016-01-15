/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.moves;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import fi.henri.chessai.game.logic.chessBoard.ChessPieces;
import java.util.HashMap;

/**
 *
 * @author manhenri
 */
public class MoveLibrary {
    private HashMap<ChessPieces, PieceMovement> library;
    private ChessBoard board;
    
    public MoveLibrary(ChessBoard board) {
        this.board = board;
        initializeLibrary();
    }

    private void initializeLibrary() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean movePiece(int actor, int target) {
        return false;
    }
}
