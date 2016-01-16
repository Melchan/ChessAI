/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.ui;

import fi.henri.chessai.game.logic.LogicHandler;
import fi.henri.chessai.game.logic.chessBoard.ChessBoard;
import fi.henri.chessai.game.logic.chessBoard.ChessPiece;
import static fi.henri.chessai.game.logic.chessBoard.ChessPiece.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

/**
 * Top most layer where pieces are drawn. Also layer where mouse is listened
 * from this component.
 *
 * @author manhenri
 */
public class ChessPiecePicture extends JLayeredPane implements MouseListener {

    private BufferedImage image;
    private ChessBoard board;
    private int paneNumber;
    private Updatetable updater;
    private HashMap<ChessPiece, String> pictures;

    public ChessPiecePicture(LogicHandler handler, int paneNumber, Updatetable updater) {
        this.board = handler.getChessBoard();
        this.updater = updater;
        this.paneNumber = paneNumber;
        this.pictures = new HashMap<ChessPiece, String>();
        this.initializeImageLibrary();
    }

    @Override
    protected void paintComponent(Graphics g) {
        image = null;
        setImage(paneNumber);
        super.paintComponent(g);

        g.drawImage(image, 0, 0, null);
    }

    private void setImage(int paneNumber) {
        ChessPiece piece = paneNumberToChessBoardSquareContent(paneNumber);
        String imagePath = findImagePath(piece);
        if (imagePath != null) {
            try {
                image = ImageIO.read(new File(imagePath));
            } catch (IOException ex) {
                System.out.println("Chess Piece image was not found.");
            }
        }
    }

    private String findImagePath(ChessPiece piece) {
        if (piece == NOTCHESSPIECE) {
            return null;
        } else {
            return pictures.get(piece);
        }
    }
    
    private ChessPiece paneNumberToChessBoardSquareContent(int paneNumber) {
        return board.boardCharToChessPiece(board.getSquareContent(paneNumber));
    }

    private void initializeImageLibrary() {
        pictures.put(BLACKKING, "src/main/resources/blackking.png");
        pictures.put(BLACKQUEEN, "src/main/resources/blackqueen.png");
        pictures.put(BLACKBISHOP, "src/main/resources/blackbishop.png");
        pictures.put(BLACKKNIGHT, "src/main/resources/blackknight.png");
        pictures.put(BLACKROOK, "src/main/resources/blackrook.png");
        pictures.put(BLACKPAWN, "src/main/resources/blackpawn.png");
        pictures.put(WHITEKING, "src/main/resources/whiteking.png");
        pictures.put(WHITEQUEEN, "src/main/resources/whitequeen.png");
        pictures.put(WHITEBISHOP, "src/main/resources/whitebishop.png");
        pictures.put(WHITEKNIGHT, "src/main/resources/whiteknight.png");
        pictures.put(WHITEROOK, "src/main/resources/whiterook.png");
        pictures.put(WHITEPAWN, "src/main/resources/whitepawn.png");
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        updater.update(paneNumber);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}