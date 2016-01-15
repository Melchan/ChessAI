/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.logic.moves;

import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import fi.henri.chessai.game.logic.chessBoard.ChessPiece;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.*;
import java.util.HashMap;

/**
 *
 * @author manhenri
 */
public class MoveLibrary {

    private HashMap<ChessPiece, PieceMovement> library;
    private ChessBoard board;

    public MoveLibrary(ChessBoard board) {
        this.board = board;
        this.library = new HashMap<ChessPiece, PieceMovement>();
        initializeLibrary();
    }

    /**
     * WHITEPAWN, WHITEROOK, WHITEKNIGHT, WHITEBISHOP, WHITEQUEEN, WHITEKING,
     * BLACKPAWN, BLACKROOK, BLACKKNIGHT, BLACKBISHOP, BLACKQUEEN, BLACKKING,
     */
    private void initializeLibrary() {
        library.put(WHITEPAWN, new PawnRules(board));
        library.put(BLACKPAWN, new PawnRules(board));

        library.put(WHITEROOK, new RookRules(board));
        library.put(BLACKROOK, new RookRules(board));

        library.put(WHITEKNIGHT, new KnightRules(board));
        library.put(BLACKKNIGHT, new KnightRules(board));

        library.put(WHITEBISHOP, new BishopRules(board));
        library.put(BLACKBISHOP, new BishopRules(board));

        library.put(WHITEQUEEN, new QueenRules(board));
        library.put(BLACKQUEEN, new QueenRules(board));

        library.put(WHITEKING, new KingRules(board));
        library.put(BLACKKING, new KingRules(board));
    }

    /**
     * Method will move chesspiece. If it is according to rules of chesspiece.
     * Also if square is empty nothign happens.
     *
     * @param actor
     * @param target
     * @return false if piece is not moved.
     */
    public boolean movePiece(int actor, int target) {
        char p = board.getSquareContent(actor);
        ChessPiece piece = board.boardCharToChessPiece(p);

        if (piece != NOTCHESSPIECE) {
            return library.get(piece).movePiece(actor, target);
        }
        return false;
    }
}
