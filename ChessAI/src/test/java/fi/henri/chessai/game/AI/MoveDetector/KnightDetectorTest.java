package fi.henri.chessai.game.AI.MoveDetector;

import fi.henri.chessai.game.logic.LogicHandler;
import fi.henri.chessai.game.dataStructure.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author manhenri
 */
public class KnightDetectorTest {

    private LogicHandler handler;
    private KnightDetector detector;
    private ArrayList<String> check;
    private ArrayList<String> result;

    @Before
    public void setUp() {
        this.handler = new LogicHandler();
        this.detector = new KnightDetector(handler);
        this.check = new ArrayList<>();
        this.result = new ArrayList<>();
    }

    @Test
    public void allowedMovesRightAtStartOfTheGame() {
        check.add("5740");
        check.add("5742");
        result.addAll(detector.possibleMoves(57));
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
    public void allowedMovesSecondTurn() {
        assertTrue(handler.movePiece(57, 42));
        assertTrue(handler.movePiece(11, 27));
        check.add("4257");
        check.add("4232");
        check.add("4236");
        check.add("4227");
        check.add("4225");
        result.addAll(detector.possibleMoves(42));
        int size = result.size();
        for (int i = 0; i < size; i++) {
            System.out.println(result.get(i));
        }
        size = result.size();
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