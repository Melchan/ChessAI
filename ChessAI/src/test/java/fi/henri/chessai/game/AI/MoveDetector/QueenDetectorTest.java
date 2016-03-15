/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI.MoveDetector;

import fi.henri.chessai.game.logic.LogicHandler;
import fi.henri.chessai.game.dataStructure.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class QueenDetectorTest {

    private LogicHandler handler;
    private QueenDetector detector;
    private ArrayList<String> check;
    private ArrayList<String> result;

    @Before
    public void setUp() {
        this.handler = new LogicHandler();
        this.detector = new QueenDetector(handler);
        this.check = new ArrayList<>();
        this.result = new ArrayList<>();
    }

    @Test
    public void allowedMovesRightAtStartOfTheGame() {
        assertEquals(true, detector.possibleMoves(56).isEmpty());
    }

    @Test
    public void limitedMoveSpaces() {
        handler.movePiece(48, 32);
        handler.movePiece(15, 31);
        check.add("5648");
        check.add("5640");
        result.addAll(detector.possibleMoves(56));
        int size = result.size();
        for (int i = 0; i < size; i++) {
            assertTrue(check.contains(result.get(i)));
        }
        size = check.size();
        for (int i = 0; i < size; i++) {
            assertTrue(result.contains(check.get(i)));
        }

        assertEquals(check.size(), result.size());
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
        int size = result.size();
        for (int i = 0; i < size; i++) {
            assertTrue(check.contains(result.get(i)));
        }
        size = check.size();
        for (int i = 0; i < size; i++) {
            assertTrue(result.contains(check.get(i)));
        }
        assertEquals(check.size(), result.size());
    }

    @Test
    public void thirdTurn() {
        assertTrue(handler.movePiece(52, 36));
        assertTrue(handler.movePiece(11, 27));
        assertTrue(handler.movePiece(59, 52));
        assertTrue(handler.movePiece(3, 11));
        assertTrue(handler.movePiece(52, 25));
        assertTrue(handler.movePiece(15, 31));
        check.add("2552");
        check.add("2543");
        check.add("2534");
        check.add("2516");
        check.add("2518");
        check.add("2511");
        check.add("2532");
        check.add("2517");
        check.add("2509");
        check.add("2524");
        check.add("2526");
        check.add("2533");
        check.add("2541");
        check.add("2527");

        result.addAll(detector.possibleMoves(25));
        System.out.println(result.size() + " " + check.size());
        int size = result.size();
        for (int i = 0; i < size; i++) {
            assertTrue(check.contains(result.get(i)));
        }
        size = check.size();
        for (int i = 0; i < size; i++) {
            assertTrue(result.contains(check.get(i)));
        }
        assertEquals(check.size(), result.size());
    }
}
