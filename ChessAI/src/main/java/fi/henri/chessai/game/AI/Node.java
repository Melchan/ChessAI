/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI;

import fi.henri.chessai.game.AI.MoveDetector.MoveObserver;
import fi.henri.chessai.game.logic.LogicHandler;
import fi.henri.chessai.game.dataStructure.ArrayList;
import java.util.HashMap;

/**
 *
 * @author manhenri
 */
public class Node {

    private LogicHandler handler;
    private HashMap<String, String> moveStates;
    private ArrayList<String> moves;
    private String state;
    private ArrayList<String> states;

    public Node(LogicHandler handler) {
        this.handler = handler;
        this.state = new String(handler.getChessBoard().getBoard());
        this.moves = new MoveObserver(handler).getPossibleMoves();
        this.states = new ArrayList<>();
        this.moveStates = new HashMap<>();
        initializeMoveStates();
    }

    private void initializeMoveStates() {
        int size = moves.size();
        if (!moves.isEmpty()) {
            for (int i = 0; i < size; i++) {
                addMoveState(moves.get(i));
            }
        }
    }

    private void addMoveState(String s) {
        int[] action = getActionFromString(s);
        handler.movePiece(action[0], action[1]);
        String moveState = new String(handler.getChessBoard().getBoard());

        states.add(moveState);
        moveStates.put(moveState, s);

        handler.rollBack(1);

    }

    private int[] getActionFromString(String s) {
        int[] action = new int[2];
        action[0] = charToInt(s.charAt(0), s.charAt(1));
        action[1] = charToInt(s.charAt(2), s.charAt(3));
        return action;
    }

    public ArrayList<String> getMoveStates() {
        return this.states;
    }

    public int getPossibleMoveCount() {
        return this.states.size();
    }

    private int charToInt(char c, char v) {
        String result = "" + c + v;
        return Integer.parseInt(result);
    }

    public String getState() {
        return state;
    }

    public int[] getMove(String state) {
        return getActionFromString(moveStates.get(state));
    }
}