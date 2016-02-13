package fi.henri.chessai.game.logic.moves;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import fi.henri.chessai.game.logic.chessBoard.ChessPiece;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.BLACKPAWN;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.NOTCHESSPIECE;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.WHITEPAWN;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

/**
 *
 * @author manhenri
 */
public class PawnRules extends PieceMovement {

    private int enPassantThreat;
    private boolean enPassant;

    public PawnRules(ChessBoard board) {
        super(board);
    }

    @Override
    protected boolean commitIfMoveIsLegal(int actor, int target) {
        enPassantThreat = 0;
        enPassant = false;
        if (allowedPawnMovement(actor, target)) {
            int i = super.getBoard().getEnPassantChange();
            if (super.getBoard().attemptToMovePiece(actor, target)) {
                commitEnPassantIfThereIsNeed(i);
                super.getBoard().setEnPassant(enPassantThreat);
                return true;
            }
        }
        return false;
    }

    private boolean allowedPawnMovement(int actor, int target) {
        int[] a = super.getBoard().indexToCoordinates(actor);
        int[] t = super.getBoard().indexToCoordinates(target);
        int xChange = Math.abs(a[0] - t[0]);
        int yChange = a[1] - t[1];
        char p = super.getBoard().getSquareContent(target);
        ChessPiece piece = super.getBoard().boardCharToChessPiece(p);
        if (isCorrectDirectionAndLenghtForColor(actor, yChange)) {
            if (moveAction(xChange) && piece == NOTCHESSPIECE) {
                if (super.pathClear(actor, target)) {
                    detectAndSetEnPassant(actor, target);
                    return true;
                }
            } else if (eatAction(actor, target, xChange)) {
                return true;
            }
        }
        return false;
    }

    private boolean moveAction(int xChange) {
        return xChange == 0;
    }

    private boolean eatAction(int actor, int target, int xChange) {
        if (xChange == 1) {
            if (occupiedByEnemy(actor, target)) {
                return true;
            } else {
                return enPassantMove(actor, target);
            }
        }
        return false;
    }
    
    private boolean occupiedByEnemy(int actor, int target) {
        Color a = super.getBoard().pieceColor(super.getBoard().getSquareContent(actor));
        Color t = super.getBoard().pieceColor(super.getBoard().getSquareContent(target));
        return a == WHITE && t == BLACK || a == BLACK && t == WHITE;
    }

    private boolean isCorrectDirectionAndLenghtForColor(int Actor, int yChange) {
        char p = super.getBoard().getSquareContent(Actor);
        boolean hasMoved = super.getBoard().hasPieceMovedInSquare(Actor);
        if (yChange == -1 || yChange == -2 && !hasMoved) {
            if (super.getBoard().pieceColor(p) == WHITE) {
                return true;
            }
        } else if (yChange == 1 || yChange == 2 && !hasMoved) {
            if (super.getBoard().pieceColor(p) == BLACK) {
                return true;
            }
        }
        return false;
    }

    private boolean enPassantMove(int actor, int target) {
        int[] t = super.getBoard().indexToCoordinates(target);
        int enP = super.getBoard().getEnPassantChange();
        int[] d = new int[2];
        d[0] = t[0];
        if (31 < actor && actor < 40) {
            d[1] = t[1] + 1;
        } else if (23 < actor && actor < 32) {
            d[1] = t[1] - 1;
        }
        return enPassantHappened(enP, d);
    }

    private void detectAndSetEnPassant(int actor, int target) {
        char[] adjancent = getAdjancentSquareContent(target);
        if (isThereThreatOfEnPassant(actor, adjancent)) {
            enPassantThreat = target;
        }
    }

    private char[] getAdjancentSquareContent(int target) {
        char[] result = new char[2];
        if (31 < target && target < 40) {
            if (target != 32) {
                result[0] = super.getBoard().getSquareContent(target - 1);
            }
            if (target != 39) {
                result[1] = super.getBoard().getSquareContent(target + 1);
            }
        } else if (23 < target && target < 32) {
            if (target != 24) {
                result[0] = super.getBoard().getSquareContent(target - 1);
            }
            if (target != 31) {
                result[1] = super.getBoard().getSquareContent(target + 1);
            }
        }
        return result;
    }

    private boolean isThereThreatOfEnPassant(int actor, char[] adjancent) {
        if (actor > 47) {
            for (int i = 0; i < 2; i++) {
                if (super.getBoard().boardCharToChessPiece(adjancent[i]) == BLACKPAWN) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < 2; i++) {
                if (super.getBoard().boardCharToChessPiece(adjancent[i]) == WHITEPAWN) {
                    return true;
                }
            }
        }
        return false;
    }

    private void commitEnPassantIfThereIsNeed(int i) {
        if (enPassant) {
            super.getBoard().attemptToEmptySquare(i);
        }
    }

    private boolean enPassantHappened(int enP, int[] d) {
        int index = super.getBoard().coordinatesToIndex(d);
        if (enP == index) {
            enPassant = true;
        }
        return enPassant;
    }
}