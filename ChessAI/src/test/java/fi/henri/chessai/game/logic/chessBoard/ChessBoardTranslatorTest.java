/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.chessBoard;

import static fi.henri.chessai.game.logic.chessBoard.ChessPieces.*;
import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manhenri
 */
public class ChessBoardTranslatorTest {
    private ChessBoardTranslator translator;
    
    @Before
    public void setUp() {
        this.translator = new ChessBoardTranslator();
    }
    
    @Test
    public void whitePawnWorks() {
        char a = 1;
        char b = 2;
        assertEquals(WHITEPAWN, translator.translate(a));
        assertEquals(WHITEPAWN, translator.translate(b));
    }
    
    @Test
    public void whiteRookWorks() {
        char a = 3;
        char b = 4;
        assertEquals(WHITEROOK, translator.translate(a));
        assertEquals(WHITEROOK, translator.translate(b));
    }
    
    @Test
    public void whiteKnightWorks() {
        char a = 5;
        char b = 6;
        assertEquals(WHITEKNIGHT, translator.translate(a));
        assertEquals(WHITEKNIGHT, translator.translate(b));
    }
    
    
    @Test
    public void whiteBishopWorks() {
        char a = 7;
        char b = 8;
        assertEquals(WHITEBISHOP, translator.translate(a));
        assertEquals(WHITEBISHOP, translator.translate(b));
    }
    
    @Test
    public void whiteQueenWorks() {
        char a = 9;
        char b = 10;
        assertEquals(WHITEQUEEN, translator.translate(a));
        assertEquals(WHITEQUEEN, translator.translate(b));
    }
    
    @Test
    public void whiteKingWorks() {
        char a = 11;
        char b = 12;
        assertEquals(WHITEKING, translator.translate(a));
        assertEquals(WHITEKING, translator.translate(b));
    }
    
    @Test
    public void blackPawnWorks() {
        char a = 13;
        char b = 14;
        assertEquals(BLACKPAWN, translator.translate(a));
        assertEquals(BLACKPAWN, translator.translate(b));
    }
   
    @Test
    public void blackRookWorks() {
        char a = 15;
        char b = 16;
        assertEquals(BLACKROOK, translator.translate(a));
        assertEquals(BLACKROOK, translator.translate(b));
    }
    
    @Test
    public void blackKnightWorks() {
        char a = 17;
        char b = 18;
        assertEquals(BLACKKNIGHT, translator.translate(a));
        assertEquals(BLACKKNIGHT, translator.translate(b));
    }
    
    @Test
    public void blackBishopWorks() {
        char a = 19;
        char b = 20;
        assertEquals(BLACKBISHOP, translator.translate(a));
        assertEquals(BLACKBISHOP, translator.translate(b));
    }
    
    @Test
    public void blackQueenWorks() {
        char a = 21;
        char b = 22;
        assertEquals(BLACKQUEEN, translator.translate(a));
        assertEquals(BLACKQUEEN, translator.translate(b));
    }
    
    @Test
    public void blackKingWorks() {
        char a = 23;
        char b = 24;
        assertEquals(BLACKKING, translator.translate(b));
        assertEquals(BLACKKING, translator.translate(a));
    }
    
    @Test 
    public void ifCharIsNotChessPieceReturnNOTCHESSPIECE() {
        char a = 0;
        char b = 25;
        char c = 100;
        assertEquals(NOTCHESSPIECE, translator.translate(a));
        assertEquals(NOTCHESSPIECE, translator.translate(b));
        assertEquals(NOTCHESSPIECE, translator.translate(c));
    }
}   