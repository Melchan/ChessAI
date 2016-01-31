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
public class BishopRules extends PieceMovement {

    public BishopRules(ChessBoard board) {
        super(board);
    }

    @Override
    protected boolean commitIfMoveIsLegal(int actor, int target) {
        if (allowedBishopMovement(actor, target)) {
            if (super.pathClear(actor, target)) {
                return super.getBoard().attemptToMovePiece(actor, target);
            }
        }
        return false;
    }

    private boolean allowedBishopMovement(int actor, int target) {
        int[] change = super.differenceBetweenTwoPoints(actor, target);

        int xChange = change[0];
        int yChange = change[1];
        
        if (yChange == 0) {
            return false;
        }
        return (xChange / yChange == 1) && (xChange % yChange == 0);
    }
}
