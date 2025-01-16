package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public int tilesize=85;
    int cols=8;
    int rows=8;

    ArrayList<Piece> piecelist = new ArrayList<>();

    public Piece selectedpiece;

    Input input = new Input(this);

    public Board(){
        this.setPreferredSize(new Dimension(tilesize*cols, tilesize*rows));
        addpieces();

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }



    public Piece getpiece(int col, int row){

        for (Piece piece : piecelist){
            if(piece.col == col && piece.row == row){
                return piece;
            }
        }

        return null;
    }


    public void makemove(Move move) {

        if (move.piece.name.equals("Pawn")) {
            movepawn(move);
        } else {
            move.piece.col = move.newcol;
            move.piece.row = move.newrow;
            move.piece.xpos = move.newcol * tilesize;
            move.piece.ypos = move.newrow * tilesize;

            move.piece.isfirstmove = false;

            capture(move.capture);
        }
    }
    private void movepawn(Move move){

        //enpassant
        int colorindex = move.piece.isWhite? 1:-1;

        if(gettilenum(move.newcol,move.newrow) == enpassanttile){
            move.capture = getpiece(move.newcol, move.newrow + colorindex);
        }

        if(Math.abs(move.piece.row - move.newrow) == 2){
            enpassanttile = gettilenum(move.newcol, move.newrow + colorindex);
        }
        else {
            enpassanttile = -1;
        }

        //promoting
        colorindex = move.piece.isWhite? 0:7;
        if(move.newrow == colorindex){
            promotepawn(move);
        }

        move.piece.col = move.newcol;
        move.piece.row = move.newrow;
        move.piece.xpos = move.newcol * tilesize;
        move.piece.ypos = move.newrow * tilesize;

        move.piece.isfirstmove = false;

        capture(move.capture);
    }

    private void promotepawn(Move move){
        piecelist.add(new Queen(this, move.newcol, move.newrow, move.piece.isWhite));
        capture(move.piece);
    }

    public int enpassanttile = -1;

    CheckScanner checkScanner = new CheckScanner(this);

    public void capture(Piece piece){
        piecelist.remove(piece);
    }

    public int gettilenum(int col, int row){
        return row * rows + col;
    }

    public boolean isvalidmove(Move move){

        if (sameteam(move.piece, move.capture)){
            return false;
        }

        if (!move.piece.isvalidmovement(move.newcol, move.newrow)){
            return false;
        }

        if (move.piece.movecollides(move.newcol, move.newrow)){
            return false;
        }

        if (checkScanner.ischecked(move)) {
            return false;
        }
        return true;
    }

    public boolean sameteam(Piece p1, Piece p2){
        if(p1 == null || p2 == null){
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }

    Piece findking(boolean isWhite) {
        for (Piece piece : piecelist) {
            if (piece.isWhite == isWhite && piece.name.equals("King")) {
                return piece;
            }
        }
        return null;
    }

    public void addpieces(){
        piecelist.add(new Knight(this,1,0,false));
        piecelist.add(new Knight(this,1,7,true));

        piecelist.add(new Knight(this,6,0,false));
        piecelist.add(new Knight(this,6,7,true));



        piecelist.add(new Rook(this,0,0,false));
        piecelist.add(new Rook(this,0,7,true));

        piecelist.add(new Rook(this,7,0,false));
        piecelist.add(new Rook(this,7,7,true));



        piecelist.add(new Bishop(this,2,0,false));
        piecelist.add(new Bishop(this,2,7,true));

        piecelist.add(new Bishop(this,5,0,false));
        piecelist.add(new Bishop(this,5,7,true));


        piecelist.add(new Queen(this,3,0,false));
        piecelist.add(new Queen(this,3,7,true));


        piecelist.add(new King(this,4,0,false));
        piecelist.add(new King(this,4,7,true));

        piecelist.add(new Pawn(this,0,1,false));
        piecelist.add(new Pawn(this,1,1,false));
        piecelist.add(new Pawn(this,2,1,false));
        piecelist.add(new Pawn(this,3,1,false));
        piecelist.add(new Pawn(this,4,1,false));
        piecelist.add(new Pawn(this,5,1,false));
        piecelist.add(new Pawn(this,6,1,false));
        piecelist.add(new Pawn(this,7,1,false));

        piecelist.add(new Pawn(this,0,6,true));
        piecelist.add(new Pawn(this,1,6,true));
        piecelist.add(new Pawn(this,2,6,true));
        piecelist.add(new Pawn(this,3,6,true));
        piecelist.add(new Pawn(this,4,6,true));
        piecelist.add(new Pawn(this,5,6,true));
        piecelist.add(new Pawn(this,6,6,true));
        piecelist.add(new Pawn(this,7,6,true));
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for (int r=0;r<rows;r++){
            for (int c=0;c<cols;c++){
                g2d.setColor(((c+r)%2==0)? new Color(239, 215, 203): new Color(159, 119, 86));
                g2d.fillRect(c*tilesize, r*tilesize, tilesize, tilesize);
            }
        }
        if(selectedpiece != null)
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                if(isvalidmove(new Move(this, selectedpiece, c, r))){
                    g2d.setColor(new Color(145, 193, 103, 103));
                    g2d.fillRect(c*tilesize, r*tilesize, tilesize, tilesize);
                }
            }
        }

        for (Piece piece : piecelist){
            piece.paint(g2d);
        }
    }
}

