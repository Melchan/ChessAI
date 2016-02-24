/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI;

import fi.henri.chessai.game.logic.LogicHandler;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class NodeTest {
    private LogicHandler handler;
    private Node node;
    private String state;
    
    @Before
    public void setUp() {
        this.handler = new LogicHandler();
        this.node = new Node(handler);
        this.state = new String(handler.getChessBoard().getBoard());
    }
    
    @Test
    public void startingPositionHasRightAmountOfPossibleMoves() {
        assertEquals(20, node.getMoveStates().size());
    }
    
    @Test
    public void afterOneMovePositionHasRightAmountOfPossibleMoves() {
        assertTrue(handler.movePiece(48, 40));
        assertTrue(handler.movePiece(6, 23));
        Node n = new Node(handler);
        assertEquals(19, n.getMoveStates().size());
    }
    
    @Test
    public void afterOneMoveStateHasStayedTheSame() {
        assertTrue(handler.movePiece(48, 40));
        assertTrue(handler.movePiece(6, 23));
        assertEquals(state, node.getState());
    }
}
