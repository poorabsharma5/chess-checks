package main;

import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {

    Board board;
    public Input(Board board){
        this.board=board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX()/board.tilesize;
        int row = e.getY()/board.tilesize;

        Piece piecexy = board.getpiece(col,row);
        if(piecexy != null){
            board.selectedpiece = piecexy;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX()/board.tilesize;
        int row = e.getY()/board.tilesize;
        if(board.selectedpiece != null){
            Move move = new Move(board, board.selectedpiece, col, row);

            if (board.isvalidmove(move)){
                board.makemove(move);
            }
            else{
                board.selectedpiece.xpos = board.selectedpiece.col * board.tilesize;
                board.selectedpiece.ypos = board.selectedpiece.row * board.tilesize;

            }
        }

        board.selectedpiece = null;
        board.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.selectedpiece != null){
            board.selectedpiece.xpos = e.getX() - board.tilesize/2;
            board.selectedpiece.ypos = e.getY() - board.tilesize/2;

            board.repaint();
        }
    }
}
