/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;

/**
 *
 * @author manhenri
 */
class PromotionHandler {

    private ChessBoard board;

    public PromotionHandler(ChessBoard board) {
        this.board = board;
    }

    /**
     * Checks if there is something to promote and promotes it.
     */
    public void checkAndPromote() {
        for (int i = 0; i < 8; i++) {
            System.out.println("i is " + i);
            ifSoldierPromote(i);
        }
        for (int i = 56; i < 64; i++) {
            System.out.println("i is " + i);
            ifSoldierPromote(i);
        }
    }

    private void ifSoldierPromote(int a) {
        char c = board.getSquareContent(a);
        if (c == 2 || c == 14) {
            promote(a);
        }
    }

    private void promote(int a) {
        System.out.println("minä teen työtä");
        char c;
        if (a < 40) {
            c = 10;
            board.attemptToPlacePiece(c, a);
        } else {
            c = 22;
            board.attemptToPlacePiece(c, a);
        }
    }
}
