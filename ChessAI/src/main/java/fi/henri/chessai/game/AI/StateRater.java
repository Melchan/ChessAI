/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import fi.henri.chessai.game.logic.chessBoard.ChessPiece;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.BLACKBISHOP;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.BLACKKING;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.BLACKKNIGHT;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.BLACKPAWN;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.BLACKQUEEN;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.BLACKROOK;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.WHITEBISHOP;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.WHITEKING;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.WHITEKNIGHT;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.WHITEPAWN;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.WHITEQUEEN;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.WHITEROOK;
import java.awt.Color;
import static java.awt.Color.YELLOW;
import java.util.HashMap;

/**
 *
 * @author manhenri
 */
public class StateRater {

    private Color player;
    private HashMap<ChessPiece, Integer> values;
    private final int pawn = 100;
    private final int rook = 510;
    private final int bishop = 333;
    private final int knight = 320;
    private final int queen = 880;
    private final int king = 0;

    public StateRater(Color player) {
        this.player = player;
        this.values = new HashMap<>();
        initialize();
    }

    /**
     * Method will return value of board from the view of player whose color has
     * been given as parameter for this class.
     *
     * @return value of current board position.
     */
    public int getBoardStateValue(char[] chessboard) {
        ChessBoard board = new ChessBoard(chessboard);
        int result = 0;
        for (int i = 0; i < 64; i++) {
            char c = board.getSquareContent(i);
            if (board.pieceColor(c) != YELLOW) {
                ChessPiece piece = board.boardCharToChessPiece(c);
                if (board.pieceColor(c) == player) {
                    result += values.get(piece);
                } else {
                    result -= values.get(piece);
                }
            }
        }
        return result;
    }

    private void initialize() {
        values.put(WHITEPAWN, pawn);
        values.put(BLACKPAWN, pawn);

        values.put(WHITEROOK, rook);
        values.put(BLACKROOK, rook);

        values.put(WHITEKNIGHT, knight);
        values.put(BLACKKNIGHT, knight);

        values.put(WHITEBISHOP, bishop);
        values.put(BLACKBISHOP, bishop);

        values.put(WHITEQUEEN, queen);
        values.put(BLACKQUEEN, queen);

        values.put(WHITEKING, king);
        values.put(BLACKKING, king);
    }
}