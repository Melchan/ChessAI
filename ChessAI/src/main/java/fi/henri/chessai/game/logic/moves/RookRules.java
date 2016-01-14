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
public class RookRules extends PieceMovement {

    public RookRules(ChessBoard board) {
        super(board);
    }

    @Override
    protected boolean commitIfMoveIsLegal(int actor, int target) {
        if (allowedRookMovement(actor, target)) {
            if (super.pathClear(actor, target)) {
                return super.getBoard().attemptToMovePiece(actor, target);
            }
        }
        return false;
    }

    private boolean allowedRookMovement(int actor, int target) {
        int[] change = super.differenceBetweenTwoPoints(actor, target);

        int xChange = change[0];
        int yChange = change[1];

        return xChange == 0 || yChange == 0;
    }
}
