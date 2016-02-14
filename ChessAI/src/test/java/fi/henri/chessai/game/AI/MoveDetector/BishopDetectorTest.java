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
public class BishopDetectorTest {

    private LogicHandler handler;
    private BishopDetector detector;
    private ArrayList<String> check;
    private ArrayList<String> result;

    @Before
    public void setUp() {
        this.handler = new LogicHandler();
        this.detector = new BishopDetector(handler);
        this.check = new ArrayList<>();
        this.result = new ArrayList<>();
    }

    @Test
    public void allowedMovesRightAtStartOfTheGame() {
        assertEquals(true, detector.possibleMoves(61).isEmpty());
    }

    @Test
    public void allowedMovesAfterPawnHasMoved() {
        assertTrue(handler.movePiece(52, 36));
        assertTrue(handler.movePiece(11, 27));
        check.add("6152");
        check.add("6143");
        check.add("6134");
        check.add("6125");
        check.add("6116");

        result.addAll(detector.possibleMoves(61));
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }

    @Test
    public void thirdTurn() {
        assertTrue(handler.movePiece(52, 36));
        assertTrue(handler.movePiece(11, 27));
        assertTrue(handler.movePiece(61, 25));
        assertTrue(handler.movePiece(3, 11));
        check.add("2552");
        check.add("2543");
        check.add("2534");
        check.add("2561");
        check.add("2516");
        check.add("2518");
        check.add("2511");
        check.add("2532");

        result.addAll(detector.possibleMoves(25));
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }
}
