/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI;

import fi.henri.chessai.game.logic.LogicHandler;
import java.awt.Color;
import fi.henri.chessai.game.dataStructure.ArrayList;
import java.util.HashMap;

/**
 *
 * @author manhenri
 */
public class UCTSearch {

    private final int win = 4006;
    private final int draw = 0;
    private final int lose = -4006;
    private final double explorationConstant = 0.3;
    private final int iterations = 1000;
    private final int depth = 12;

    private LogicHandler handler;
    private HashMap<String, Double> pointMemory;
    private HashMap<String, Integer> searchMemory;
    private HashMap<String, Node> tree;
    private Color player;
    private StateRater rater;

    public UCTSearch(LogicHandler handler, Color player) {
        this.handler = handler;
        this.pointMemory = new HashMap<>();
        this.searchMemory = new HashMap<>();
        this.tree = new HashMap<>();
        this.player = player;
        this.rater = new StateRater(player);
    }

    /**
     * Asking to AI to do it's move
     */
    public void commitMove() {
        int time = iterations;
        int turnCount = handler.getMoveCount();
        while (time > 0) {
            simTree();
            backUp(turnCount);
            time--;
        }
        if (notGameOver()) {
            selectMove(getCurrentStateKey());
        }
        tree.clear();
    }

    private void simTree() {
        int t = depth;
        while (t > 0 && notGameOver()) {
            if (notGameOver()) {
                String key = getCurrentStateKey();
                if (!tree.containsKey(key)) {
                    tree.put(key, new Node(handler));
                }

                selectMove(key);

                t--;
            } else {
                break;
            }
        }
    }

    private boolean notGameOver() {
        return !handler.getCheckMate() && !handler.getDraw();
    }

    private void selectMove(String key) {
        ArrayList<String> bestMoves = rateAllMovesInCurrentStateGiveBiggest(key);
        if (notGameOver() && !bestMoves.isEmpty()) {
            int[] move = chooseMove(bestMoves, key);
            handler.movePiece(move[0], move[1]);
        }
    }

    private ArrayList<String> rateAllMovesInCurrentStateGiveBiggest(String key) {
        if (!tree.containsKey(key)) {
            tree.put(key, new Node(handler));
        }
        ArrayList<String> states = tree.get(key).getMoveStates();
        ArrayList<String> movePoints = new ArrayList<>();
        int size = states.size();
        for (int i = 0; i < size; i++) {
            setPoints(states.get(i));
            keepRightPoints(states.get(i), movePoints);
        }
        return movePoints;
    }

    private void setPoints(String s) {
        double result;
        if (!pointMemory.containsKey(s)) {
            double ratedBoard = rater.getBoardStateValue(s.toCharArray());
            if (handler.getTurn() == player) {
                result = getRegret(s) + ratedBoard;
            } else {
                result = -getRegret(s) + ratedBoard;
            }
            pointMemory.put(s, result);
        }
    }

    private double getRegret(String s) {
        double c = explorationConstant;
        if (!searchMemory.containsKey(s)) {
            return win * c;
        }
        int moves = getUnExplored(s);
        int exploration = getExplorationCount(s);

        return c * Math.sqrt(Math.log1p(moves) / (double) exploration);
    }

    private int getExplorationCount(String s) {
        if (searchMemory.containsKey(s)) {
            return searchMemory.get(s);
        }
        return 1;
    }

    private int getUnExplored(String s) {
        int result;
        result = tree.get(s).getPossibleMoveCount();
        if (!searchMemory.containsKey(s)) {
            return result;
        }
        return result - searchMemory.get(s);
    }

    private void keepBiggest(String key, ArrayList<String> list) {
        double d = pointMemory.get(key);
        if (list.isEmpty()) {
            list.add(key);
        } else {
            double biggest = pointMemory.get(list.get(0));
            if (d > biggest) {
                list.clear();
                list.add(key);
            } else if (d == biggest) {
                list.add(key);
            }
        }
    }

    private void keepSmallest(String key, ArrayList<String> list) {
        double d = pointMemory.get(key);
        if (list.isEmpty()) {
            list.add(key);
        } else {
            double smallest = pointMemory.get(list.get(0));
            if (d < smallest) {
                list.clear();
                list.add(key);
            } else if (d == smallest) {
                list.add(key);
            }
        }
    }

    private void keepRightPoints(String s, ArrayList<String> movePoints) {
        if (handler.getTurn() == player) {
            keepBiggest(s, movePoints);
        } else {
            keepSmallest(s, movePoints);
        }
    }

    private int[] chooseMove(ArrayList<String> bestMoves, String node) {
        int size = bestMoves.size();
        double random = Math.random();
        int index = (int) ((double) size * random);
        String key = bestMoves.get(index);
        return tree.get(node).getMove(key);

    }

    private double endGamePoints() {
        if (handler.getCheckMate()) {
            if (handler.getTurn() == player) {
                return lose;
            } else {
                return win;
            }
        }
        return draw;
    }

    private void backUp(int turnCount) {
        while (handler.getMoveCount() > turnCount) {
            handler.rollBack(1);
            String key = getCurrentStateKey();
            int count = 1;
            if (searchMemory.containsKey(key)) {
                count = searchMemory.get(key) + 1;
            }
            searchMemory.put(key, count);
            updatePoints(key);
        }
    }

    private void updatePoints(String s) {
        double result;
        if (!notGameOver()) {
            double average = averagePoints(s);
            double ratedBoard = rater.getBoardStateValue(s.toCharArray());
            if (handler.getTurn() == player) {
                result = average + getRegret(s) + ratedBoard;
            } else {
                result = average - getRegret(s) + ratedBoard;
            }
        } else {
            result = endGamePoints();
        }
        pointMemory.put(s, result);
    }

    private double averagePoints(String s) {
        ArrayList<String> list = tree.get(s).getMoveStates();
        double sum = 0;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (pointMemory.containsKey(list.get(i))) {
                sum += pointMemory.get(list.get(i));
            }
        }
        int visited = this.searchMemory.get(s);
        return sum / visited;
    }

    private String getCurrentStateKey() {
        return new String(handler.getChessBoard().getBoard());
    }
}