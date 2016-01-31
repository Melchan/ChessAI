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
public class KingRules extends PieceMovement {

    private int castling;
    private int tower;

    public KingRules(ChessBoard board) {
        super(board);
    }

    @Override
    protected boolean commitIfMoveIsLegal(int actor, int target) {
        castling = -1;
        if (allowedKingMovement(actor, target)) {
            if (super.getBoard().attemptToMovePiece(actor, target)) {
                commitCastlingIfNecessary();
                return true;
            }
        }
        return false;
    }

    private boolean allowedKingMovement(int actor, int target) {
        int[] change = super.differenceBetweenTwoPoints(actor, target);

        int xChange = change[0];
        int yChange = change[1];
        
        if (allowedChange(xChange, yChange)) {
            return true;
        } else if (yChange == 0 && castling(actor, target)) {
            return true;
        }
        
        return false;
    }

    private void commitCastlingIfNecessary() {
        if (castling != -1) {
            char p = super.getBoard().getSquareContent(tower);
            super.getBoard().attemptToEmptySquare(tower);
            super.getBoard().attemptToPlacePiece(p, castling);
            super.getBoard().setPieceToMoved(castling);
        }
    }
    
    private boolean allowedChange(int xC, int yC) {
        return (-1 < xC && xC < 2) && (-1 < yC && yC < 2);
    }

    private boolean castling(int actor, int target) {
        if (super.getBoard().hasPieceMovedInSquare(actor) == false) {
            int[] t = super.getBoard().indexToCoordinates(target);
            if (t[0] == 2) {
                t[0] = 0;
            } else if ((t[0] == 6)) {
                t[0] = 7;
            } else {
                return false;
            }
            int rook = super.getBoard().coordinatesToIndex(t);
            return setCastlingIfPossible(actor, rook);
        }
        return false;
    }

    private boolean setCastlingIfPossible(int actor, int rook) {
        if (super.getBoard().hasPieceMovedInSquare(rook) == false) {
            if (super.pathClear(actor, rook)) {
                int[] t = super.getBoard().indexToCoordinates(rook);
                if (t[0] == 0) {
                    t[0] = 3;
                } else {
                    t[0] = 5;
                }
                this.castling = super.getBoard().coordinatesToIndex(t);
                this.tower = rook;
                return true;
            }
        }
        return false;
    }
}