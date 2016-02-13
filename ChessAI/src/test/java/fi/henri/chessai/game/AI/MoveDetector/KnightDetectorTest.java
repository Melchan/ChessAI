package fi.henri.chessai.game.AI.MoveDetector;

import fi.henri.chessai.game.logic.LogicHandler;
import java.util.ArrayList;
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
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
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
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
    }
}
