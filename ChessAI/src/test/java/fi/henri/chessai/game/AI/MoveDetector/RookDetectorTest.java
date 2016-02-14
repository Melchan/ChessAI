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
public class RookDetectorTest {

    private LogicHandler handler;
    private RookDetector detector;
    private ArrayList<String> check;
    private ArrayList<String> result;

    @Before
    public void setUp() {
        this.handler = new LogicHandler();
        this.detector = new RookDetector(handler);
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
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }

    @Test
    public void limitedMmoveSpacesXaxis() {
        handler.movePiece(48, 32);
        handler.movePiece(15, 31);
        handler.movePiece(56, 40);
        handler.getChessBoard().changeTurn();
        check.add("4056");
        check.add("4048");
        check.add("4041");
        check.add("4042");
        check.add("4043");
        check.add("4044");
        check.add("4045");
        check.add("4046");
        check.add("4047");
        result.addAll(detector.possibleMoves(40));
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }

    @Test
    public void limitedMmoveSpacesXaxisMinusSide() {
        assertTrue(handler.movePiece(48, 32));
        assertTrue(handler.movePiece(15, 31));
        assertTrue(handler.movePiece(56, 40));
        assertTrue(handler.movePiece(8, 24));
        assertTrue(handler.movePiece(40, 41));
        assertTrue(handler.movePiece(14, 30));
        check.add("4140");
        check.add("4142");
        check.add("4143");
        check.add("4144");
        check.add("4145");
        check.add("4146");
        check.add("4147");
        check.add("4133");
        check.add("4125");
        check.add("4117");
        check.add("4109");

        result.addAll(detector.possibleMoves(41));
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }

    @Test
    public void ifKingInCheckWillWorkTheRightWay() {
        assertTrue(handler.movePiece(48, 32));
        assertTrue(handler.movePiece(12, 20));
        assertTrue(handler.movePiece(56, 40));
        assertTrue(handler.movePiece(3, 30));
        assertTrue(handler.movePiece(52, 44));
        assertTrue(handler.movePiece(30, 44));
        check.add("4044");
        
        result.addAll(detector.possibleMoves(40));
        for (String s : result) {
            assertTrue(check.contains(s));
        }
        for (String s : check) {
            assertTrue(result.contains(s));
        }
        assertEquals(check.size(), result.size());
    }
}
