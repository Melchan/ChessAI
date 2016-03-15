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
import fi.henri.chessai.game.dataStructure.ArrayList;
import java.util.HashMap;

/**
 *
 * @author manhenri
 */
public class DrawObserver {

    private ChessBoard board;
    private HashMap<String, Integer> repetitionHistory;
    private CheckMateObserver observer;

    public DrawObserver(ChessBoard board, CheckMateObserver observer) {
        this.board = board;
        this.observer = observer;
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
        } else {
            return cannotMoveSingleKing();
        }
    }

    /**
     * Method will erase current gameState from it's memory;
     */
    public void rollBack() {

        String key = new String(board.getBoard());
        if (repetitionHistory.containsKey(key)) {
            int i = repetitionHistory.get(key);
            i--;
            this.repetitionHistory.put(key, i);
        }
    }

    private boolean threeFoldRepetition() {
        String key = new String(board.getBoard());
        if (repetitionHistory.containsKey(key)) {
            Integer value = repetitionHistory.get(key);
            value++;
            repetitionHistory.put(key, value);
            return value > 2;
        } else {
            repetitionHistory.put(key, 1);
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
            } else if (badPiecesOnBoard(pieces)) {
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
        int size = pieces.size();
        for (int i = 0; i < size; i++) {
            names.add(board.boardCharToChessPiece(pieces.get(i)));
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
        int size = names.size();
        for (int i = 0; i < size; i++) {
            if (names.get(i) == WHITEBISHOP || names.get(i) == BLACKBISHOP) {
                return true;
            }
        }
        return false;
    }

    private boolean identicalKingAndBishopOnBothSides(ArrayList<ChessPiece> names) {
        boolean white = false;
        boolean black = false;
        int size = names.size();
        for (int i = 0; i < size; i++) {
            if (names.get(i) == WHITEBISHOP) {
                white = true;
            } else if (names.get(i) == BLACKBISHOP) {
                black = true;
            }
        }
        if (white && black) {
            return areBishopsOnSameColor();
        }
        return false;
    }

    private boolean kingAndKnight(ArrayList<ChessPiece> names) {
        int size = names.size();
        for (int i = 0; i < size; i++) {
            if (names.get(i) == WHITEKNIGHT || names.get(i) == BLACKKNIGHT) {
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

    private boolean cannotMoveSingleKing() {
        return observer.kingCannotMove();
    }
}
