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

    @Before
    public void setUp() {
        this.handler = new LogicHandler();
        this.detector = new RookDetector(handler);
        this.check = new ArrayList<>();
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
        for (String s : detector.possibleMoves(48)) {
            assertTrue(check.contains(s));
        }
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
        check.add("3012");
        for (String s : detector.possibleMoves(40)) {
            assertEquals(true, check.contains(s));
        }
    }
    
    @Test
    public void limitedMmoveSpacesXaxisMinusSide() {
        handler.movePiece(48, 32);
        handler.movePiece(15, 31);
        handler.movePiece(56, 40);
        handler.movePiece(8, 24);
        check.add("4056");
        check.add("4048");
        check.add("4041");
        check.add("4042");
        check.add("4043");
        check.add("4044");
        check.add("4045");
        check.add("4046");
        check.add("4047");
        for (String s : detector.possibleMoves(40)) {
            assertTrue(check.contains(s));
        }
    }
}
