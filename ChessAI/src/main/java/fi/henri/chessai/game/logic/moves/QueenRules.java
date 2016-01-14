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
public class QueenRules extends PieceMovement{
    private RookRules rRules;
    private BishopRules bRules;

    public QueenRules(ChessBoard board) {
        super(board);
        this.rRules = new RookRules(board);
        this.bRules = new BishopRules(board);
    }

    @Override
    protected boolean commitIfMoveIsLegal(int actor, int target) {
        if (rRules.commitIfMoveIsLegal(actor, target)) {
            return true;
        } else {
            return bRules.commitIfMoveIsLegal(actor, target);
        }
    }  
}
