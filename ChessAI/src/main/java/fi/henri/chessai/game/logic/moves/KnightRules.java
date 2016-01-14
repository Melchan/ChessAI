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
public class KnightRules extends PieceMovement {

    public KnightRules(ChessBoard board) {
        super(board);
    }

    @Override
    protected boolean commitIfMoveIsLegal(int actor, int target) {
        if (allowedKnightMovement(actor, target)) {
            return super.getBoard().attemptToMovePiece(actor, target);
        }
        return false;
    }

    private boolean allowedKnightMovement(int actor, int target) {
        int[] change = super.differenceBetweenTwoPoints(actor, target);

        int xChange = change[0];
        int yChange = change[1];

        return xChange == 2 && yChange == 1 || xChange == 1 && yChange == 2;
    }
}
