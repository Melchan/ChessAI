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
public class PawnDetectorTest {
    private LogicHandler handler;
    private PawnDetector detector;
    private ArrayList<String> check;
    private ArrayList<String> result;
    
    @Before
    public void setUp() {
        this.handler = new LogicHandler();
        this.detector = new PawnDetector(handler);
        this.check = new ArrayList<>();
        this.result = new ArrayList<>();
    }
    
    @Test
    public void allowedMovesRightAtStartOfTheGame() {
        check.add("4840");
        check.add("4832");
        result.addAll(detector.possibleMoves(48));
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }
    
    @Test
    public void allowedMovesBlackTurn() {
        handler.movePiece(51, 35);
        check.add("1220");
        check.add("1228");
        result.addAll(detector.possibleMoves(12));
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }
    
    @Test
    public void allowedMovesWhiteSecondTurn() {
        handler.movePiece(51, 35);
        handler.movePiece(12, 28);
        check.add("3527");
        check.add("3528");
        result.addAll(detector.possibleMoves(35));
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
    }
    
    @Test
    public void allowedMovesWhiteThirdTurn() {
        handler.movePiece(51, 35);
        handler.movePiece(12, 28);
        handler.movePiece(35, 28);
        handler.movePiece(13, 29);
        check.add("2821");
        check.add("2820");
        result.addAll(detector.possibleMoves(28));
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }
    
    @Test
    public void allowedMovesBlackSecondTurn() {
        assertTrue(handler.movePiece(51, 35));
        assertTrue(handler.movePiece(12, 28));
        assertTrue(handler.movePiece(35, 27));
        check.add("1119");
        result.addAll(detector.possibleMoves(11));
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }
}