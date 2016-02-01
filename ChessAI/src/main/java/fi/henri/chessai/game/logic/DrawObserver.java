/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import fi.henri.chessai.game.logic.chessBoard.ChessPiece;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.BLACKBISHOP;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.BLACKKNIGHT;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.WHITEBISHOP;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.WHITEKNIGHT;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author manhenri
 */
public class DrawObserver {

    private ChessBoard board;
    private HashMap<String, Integer> repetitionHistory;

    public DrawObserver(ChessBoard board) {
        this.board = board;
        this.repetitionHistory = new HashMap<String, Integer>();
    }

    /**
     * Saves current board situation and will tell if game is in a draw.
     *
     * @return
     */
    public boolean isDraw() {
        if (threeFoldRepetition()) {
            return true;
        } else if (turnCapAchieved()) {
            return true;
        } else if (checkMateIsNotPossible()) {
            return true;
        }
        return false;
    }

    private boolean threeFoldRepetition() {
        String data = new String(board.getBoard());
        if (repetitionHistory.containsKey(data)) {
            Integer value = repetitionHistory.get(data);
            value++;
            repetitionHistory.put(data, value);
            return value > 2;
        } else {
            repetitionHistory.put(data, 1);
        }
        return false;
    }

    private boolean turnCapAchieved() {
        return board.getHistory().size() > 100;
    }

    private boolean checkMateIsNotPossible() {
        ArrayList<Character> pieces = piecesOnBoard();
        if (pieces.size() <= 4) {
            if (pieces.size() <= 2) {
                return true;
            }
            else if (badPiecesOnBoard(pieces)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Character> piecesOnBoard() {
        char[] b = board.getBoard();
        ArrayList<Character> pieces = new ArrayList<>();
        for (int i = 0; i < b.length; i++) {
            if (b[i] != 0) {
                pieces.add(b[i]);
            }
        }
        return pieces;
    }

    private boolean badPiecesOnBoard(ArrayList<Character> pieces) {
        ArrayList<ChessPiece> names = new ArrayList<>();
        for (Character c : pieces) {
            names.add(board.boardCharToChessPiece(c));
        }
        if (identicalKingAndBishopOnBothSides(names)) {
            return true;
        } else if (names.size() < 4) {
            if (kingAndBishop(names)) {
                return true;
            } else if (kingAndKnight(names)) {
                return true;
            }
        }
        return false;
    }

    private boolean kingAndBishop(ArrayList<ChessPiece> names) {
        for (ChessPiece p : names) {
            if (p == WHITEBISHOP || p == BLACKBISHOP) {
                return true;
            }
        }
        return false;
    }

    private boolean identicalKingAndBishopOnBothSides(ArrayList<ChessPiece> names) {
        boolean white = false;
        boolean black = false;
        for (ChessPiece p : names) {
            if (p == WHITEBISHOP) {
                white = true;
            } else if (p == BLACKBISHOP) {
                black = true;
            }
        }
        if (white && black) {
            return areBishopsOnSameColor();
        }
        return false;
    }

    private boolean kingAndKnight(ArrayList<ChessPiece> names) {
        for (ChessPiece p : names) {
            if (p == WHITEKNIGHT || p == BLACKKNIGHT) {
                return true;
            }
        }
        return false;
    }

    private boolean areBishopsOnSameColor() {
        Color white = RED;
        Color black = BLUE;
        char[] b = board.getBoard();
        for (int i = 0; i < b.length; i++) {
            if (board.boardCharToChessPiece(b[i]) == WHITEBISHOP) {
                white = squareColor(i);
                System.out.println(white);
            } else if (board.boardCharToChessPiece(b[i]) == BLACKBISHOP) {
                black = squareColor(i);
                System.out.println(black);
            }
        }
        return black == white;
    }
    
    private Color squareColor(int i) {
        int[] c = board.indexToCoordinates(i);
        System.out.println("x " + c[0] + " y " + c[1]);
        if (isBlackSquare(c)) {
            return BLACK;
        } else {
            return WHITE;
        }
    }
    
    private boolean isBlackSquare(int[] c) {
        if (c[0] % 2 == 0 && c[1] % 2 == 0) {
            return true;
        } else if (c[0] % 2 != 0 && c[1] % 2 != 0) {
            return true;
        }
        return false;
    }
}
