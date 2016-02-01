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
                if (super.getBoard().attemptToMovePiece(actor, target)) {
                    ifNoCastlingMakeKingMoved(actor);
                    return true;
                }

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

    private void ifNoCastlingMakeKingMoved(int actor) {
        if (actor == 0 || actor == 7) {
            if (blackRooksHaveMoved()) {
                super.getBoard().setPieceToMoved(4);
            }
        } else if (actor == 63 || actor == 56) {
            if (whiteRooksHaveMoved()) {
                super.getBoard().setPieceToMoved(60);
            }
        }
    }
    
    private boolean blackRooksHaveMoved() {
        return super.getBoard().hasPieceMovedInSquare(0) && super.getBoard().hasPieceMovedInSquare(7);
    }
    
    private boolean whiteRooksHaveMoved() {
        return super.getBoard().hasPieceMovedInSquare(63) && super.getBoard().hasPieceMovedInSquare(56);
    }
}
