/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.chessBoard;

/**
 * Class is dedicated for creating new game.
 *
 * @author manhenri
 */
public class ChessBoardInitializer {

    private ChessBoard board;

    public ChessBoardInitializer(ChessBoard board) {
        this.board = board;
    }

    /**
     *method will set board to staring position.
     */
    public void newGame() {
        this.board.initialize();
        setChessPiecesOnBoard();
    }

    private void setChessPiecesOnBoard() {
        setPawnsOnBoard();
        setRooksOnBoard();
        setKnightsOnBoard();
        setBishopsOnBoard();
        setQueensOnBoard();
        setKingsOnBoard();
    }

    private void setPawnsOnBoard() {
        setWhitePawns();
        setBlackPawns();
    }

    private void setRooksOnBoard() {
        setWhiteRooks();
        setBlackRooks();
    }

    private void setKnightsOnBoard() {
        setWhiteKnights();
        setBlackKnights();
    }

    private void setBishopsOnBoard() {
        setWhiteBishops();
        setBlackBishops();
    }

    private void setQueensOnBoard() {
        char w = 9;
        char b = 21;
        board.attemptToPlacePiece(w, 59);
        board.attemptToPlacePiece(b, 3);
    }

    private void setKingsOnBoard() {
        char w = 11;
        char b = 23;
        board.attemptToPlacePiece(w, 60);
        board.attemptToPlacePiece(b, 4);
    }

    private void setWhitePawns() {
        char p = 1;
        for (int i = 48; i < 56; i++) {
            board.attemptToPlacePiece(p, i);
        }
    }

    private void setBlackPawns() {
        char p = 13;
        for (int i = 8; i < 16; i++) {
            board.attemptToPlacePiece(p, i);
        }
    }

    private void setWhiteRooks() {
        char p = 3;
        board.attemptToPlacePiece(p, 56);
        board.attemptToPlacePiece(p, 63);
    }

    private void setBlackRooks() {
        char p = 15;
        board.attemptToPlacePiece(p, 0);
        board.attemptToPlacePiece(p, 7);
    }

    private void setWhiteKnights() {
        char p = 5;
        board.attemptToPlacePiece(p, 57);
        board.attemptToPlacePiece(p, 62);
    }

    private void setBlackKnights() {
        char p = 17;
        board.attemptToPlacePiece(p, 1);
        board.attemptToPlacePiece(p, 6);
    }

    private void setWhiteBishops() {
        char p = 7;
        board.attemptToPlacePiece(p, 58);
        board.attemptToPlacePiece(p, 61);
    }

    private void setBlackBishops() {
        char p = 19;
        board.attemptToPlacePiece(p, 2);
        board.attemptToPlacePiece(p, 5);
    }
}
