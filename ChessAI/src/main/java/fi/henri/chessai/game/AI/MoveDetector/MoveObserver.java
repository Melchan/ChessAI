/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.AI.MoveDetector;

import fi.henri.chessai.game.logic.LogicHandler;
import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import fi.henri.chessai.game.logic.chessBoard.ChessPiece;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.*;
import fi.henri.chessai.game.dataStructure.ArrayList;
import fi.henri.chessai.game.dataStructure.HashMap;

/**
 *
 * @author manhenri
 */
public class MoveObserver {

    private LogicHandler handler;
    private ChessBoard board;
    private HashMap<ChessPiece, MoveDetector> moveDetectorLibrary;

    public MoveObserver(LogicHandler handler) {
        this.handler = handler;
        this.board = handler.getChessBoard();
        this.moveDetectorLibrary = new HashMap<ChessPiece, MoveDetector>();
        initializeMoveLibrary();
    }

    /**
     * Gives all possible moves for current player in string form. Example
     * result would be "0409" = 4 and 9 or "6200" = 62 and 0.
     *
     * @return
     */
    public ArrayList<String> getPossibleMoves() {
        ArrayList<Integer> ownPieces = ownPieceLocations();
        ArrayList<String> result = getMoves(ownPieces);
        return result;
    }

    private ArrayList<Integer> ownPieceLocations() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < 64; i++) {
            if (board.pieceColor(board.getSquareContent(i)) == board.getTurn()) {
                result.add(i);
            }
        }
        return result;
    }
    
    private ArrayList<String> getMoves(ArrayList<Integer> ownPieces) {
        ArrayList<String> result = new ArrayList<String>();
        int size = ownPieces.size();
        for (int i = 0; i < size; i++) {
            ChessPiece piece = board.boardCharToChessPiece(board.getSquareContent(ownPieces.get(i)));
            result.addAll(moveDetectorLibrary.get(piece).possibleMoves(ownPieces.get(i)));
        }
        return result;
    }
    
    private void initializeMoveLibrary() {
        PawnDetector pawn = new PawnDetector(handler);
        moveDetectorLibrary.put(WHITEPAWN, pawn);
        moveDetectorLibrary.put(BLACKPAWN, pawn);
        
        RookDetector rook = new RookDetector(handler);
        moveDetectorLibrary.put(WHITEROOK, rook);
        moveDetectorLibrary.put(BLACKROOK,rook);
        
        KnightDetector knight = new KnightDetector(handler);
        moveDetectorLibrary.put(WHITEKNIGHT, knight);
        moveDetectorLibrary.put(BLACKKNIGHT, knight);
        
        BishopDetector bishop = new BishopDetector(handler);
        moveDetectorLibrary.put(WHITEBISHOP, bishop);
        moveDetectorLibrary.put(BLACKBISHOP, bishop);
        
        QueenDetector queen = new QueenDetector(handler);
        moveDetectorLibrary.put(WHITEQUEEN, queen);
        moveDetectorLibrary.put(BLACKQUEEN, queen);
        
        KingDetector king = new KingDetector(handler);
        moveDetectorLibrary.put(WHITEKING, king);
        moveDetectorLibrary.put(BLACKKING, king);
    }
}