/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.moves;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;

/**
 *
 * @author manhenri
 */
public class PawnRules extends PieceMovement {

    public PawnRules(ChessBoard board) {
        super(board);
    }

    @Override
    protected boolean commitIfMoveIsLegal(int actor, int target) {
        return false;
    }

}
