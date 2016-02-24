/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI;

import fi.henri.chessai.game.logic.LogicHandler;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author manhenri
 */
public class UCTSearch {

    private final int win = 4006;
    private final int draw = 0;
    private final int lose = -4006;
    private final double explorationConstant = 0.5;
    private final int iterations = 100;
    private final int depth = 10;

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

    public void commitMove() {
        int time = iterations;
        int turnCount = handler.getMoveCount();
        while (time > 0) {
            simTree();
            backUp(turnCount);
            time--;
        }
        tree.clear();
        selectMove();
    }

    private void selectMove() {
        ArrayList<String> bestMoves = rateAllMovesInCurrentStateGiveBiggest();
        int[] move = chooseMove(bestMoves);
        handler.movePiece(move[0], move[1]);
    }

    private void simTree() {
        int t = depth;
        while (t > 0 && notGameOver()) {
            String key = new String(handler.getChessBoard().getBoard());
            if (tree.containsKey(key)) {
                tree.put(key, new Node(handler));
            }
            selectMove();
            t--;
        }
    }

    private boolean notGameOver() {
        return handler.getCheckMate() || handler.getDraw();
    }

    private ArrayList<String> rateAllMovesInCurrentStateGiveBiggest() {
        String key = new String(handler.getChessBoard().getBoard());
        ArrayList<String> states = tree.get(key).getMoveStates();
        ArrayList<String> movePoints = new ArrayList<>();
        for (String s : states) {
            setPoints(s);
            keepRightPoints(s, movePoints);
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
        int moves = getUnExplored(s);
        int exploration = getExplorationCount(s);
        double c = explorationConstant;
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
        double biggest = pointMemory.get(list.get(0));
        if (d > biggest) {
            list.clear();
            list.add(key);
        } else if (d == biggest) {
            list.add(key);
        }
    }

    private void keepSmallest(String key, ArrayList<String> list) {
        double d = pointMemory.get(key);
        double smallest = pointMemory.get(list.get(0));
        if (d < smallest) {
            list.clear();
            list.add(key);
        } else if (d == smallest) {
            list.add(key);
        }
    }

    private void keepRightPoints(String s, ArrayList<String> movePoints) {
        if (handler.getTurn() == player) {
            keepBiggest(s, movePoints);
        } else {
            keepSmallest(s, movePoints);
        }
    }

    private int[] chooseMove(ArrayList<String> bestMoves) {
        int size = bestMoves.size();
        double random = Math.random();
        int index = (int) ((double) size * random);
        String key = bestMoves.get(index);
        return tree.get(key).getMove(key);
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
        while (handler.getMoveCount() != turnCount) {
            handler.getChessBoard().rollBack(1);
            String key = new String(handler.getChessBoard().getBoard());
            int count = searchMemory.get(key) + 1;
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
        for (String k : list) {
            if (pointMemory.containsKey(k)) {
                sum += pointMemory.get(k);
            }
        }
        int visited = this.searchMemory.get(s);
        return sum / visited;
    }
}
