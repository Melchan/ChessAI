/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.chessBoard;

import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.*;
import java.util.HashMap;

/**
 *
 * @author manhenri
 */
public class ChessBoardTranslator {

    private HashMap<Character, ChessPiece> library;

    public ChessBoardTranslator() {
        this.library = new HashMap<Character, ChessPiece>();
        initializeLibrary();
    }

    /**
     * Will give translate char to chesspiece enum. If char is not a chess piece
     * enum will be NOTCHESSPIECE.
     *
     * @param c
     * @return
     */
    public ChessPiece translate(char c) {
        c = changeToEvenNumber(c);
        if (0 < c && c < 25) {
            return this.library.get(c);
        }
        return NOTCHESSPIECE;
    }
    
    private char changeToEvenNumber(char c) {
        if (c % 2 == 1) {
            c++;
        }
        return c;
    }

    private void initializeLibrary() {
        char a = 2;
        this.library.put(a, WHITEPAWN);
        a = 4;
        this.library.put(a, WHITEROOK);
        a = 6;
        this.library.put(a, WHITEKNIGHT);
        a = 8;
        this.library.put(a, WHITEBISHOP);
        a = 10;
        this.library.put(a , WHITEQUEEN);
        a = 12;
        this.library.put(a, WHITEKING);
        a = 14;
        this.library.put(a, BLACKPAWN);
        a = 16;
        this.library.put(a, BLACKROOK);
        a = 18;
        this.library.put(a, BLACKKNIGHT);
        a = 20;
        this.library.put(a, BLACKBISHOP);
        a = 22;
        this.library.put(a, BLACKQUEEN);
        a = 24;
        this.library.put(a, BLACKKING);        
    }
}
