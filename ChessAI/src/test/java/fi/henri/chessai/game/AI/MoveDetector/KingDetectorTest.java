/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI.MoveDetector;

import fi.henri.chessai.game.logic.LogicHandler;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class KingDetectorTest {

    private LogicHandler handler;
    private KingDetector detector;
    private ArrayList<String> check;
    private ArrayList<String> result;

    @Before
    public void setUp() {
        this.handler = new LogicHandler();
        this.detector = new KingDetector(handler);
        this.check = new ArrayList<>();
        this.result = new ArrayList<>();
    }

    @Test
    public void allowedMovesRightAtStartOfTheGame() {
        result.addAll(detector.possibleMoves(60));
        assertEquals(0, result.size());
    }

    @Test
    public void castlingCheckWhiteLeftSide() {
        assertTrue(handler.movePiece(51, 35));
        assertTrue(handler.movePiece(11, 27));
        assertTrue(handler.movePiece(52, 36));
        assertTrue(handler.movePiece(12, 28));
        assertTrue(handler.movePiece(58, 37));
        assertTrue(handler.movePiece(5, 26));
        assertTrue(handler.movePiece(61, 34));
        assertTrue(handler.movePiece(2, 29));
        assertTrue(handler.movePiece(59, 51));
        assertTrue(handler.movePiece(3, 11));
        assertTrue(handler.movePiece(57, 42));
        assertTrue(handler.movePiece(6, 21));
        assertTrue(handler.movePiece(62, 45));
        assertTrue(handler.movePiece(1, 18));
        assertTrue(handler.movePiece(51, 43));
        assertTrue(handler.movePiece(11, 19));
        check.add("6061");
        check.add("6062");
        check.add("6059");
        check.add("6058");
        check.add("6052");
        check.add("6051");
        result.addAll(detector.possibleMoves(60));
        for (String s : result) {
            System.out.println(s);
            assertTrue(check.contains(s));
        }
        System.out.println("");
        for (String s : check) {
            System.out.println(s);
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }

    @Test
    public void castlingCheckBlackRightSide() {
        assertTrue(handler.movePiece(51, 35));
        assertTrue(handler.movePiece(11, 27));
        assertTrue(handler.movePiece(52, 36));
        assertTrue(handler.movePiece(12, 28));
        assertTrue(handler.movePiece(58, 37));
        assertTrue(handler.movePiece(5, 26));
        assertTrue(handler.movePiece(61, 34));
        assertTrue(handler.movePiece(2, 29));
        assertTrue(handler.movePiece(59, 51));
        assertTrue(handler.movePiece(3, 11));
        assertTrue(handler.movePiece(57, 42));
        assertTrue(handler.movePiece(6, 21));
        assertTrue(handler.movePiece(62, 45));
        assertTrue(handler.movePiece(1, 18));
        assertTrue(handler.movePiece(60, 58));
        assertTrue(handler.movePiece(11, 19));
        assertTrue(handler.movePiece(58, 57));
        
        check.add("0402");
        check.add("0403");
        check.add("0405");
        check.add("0406");
        check.add("0412");
        check.add("0411");
        result.addAll(detector.possibleMoves(4));
        for (String s : result) {
            System.out.println(s);
            assertTrue(check.contains(s));
        }
        System.out.println("");
        for (String s : check) {
            System.out.println(s);
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }
}
