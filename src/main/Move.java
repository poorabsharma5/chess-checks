package main;

import pieces.Piece;

public class Move {

    int oldcol;
    int oldrow;

    int newcol;
    int newrow;

    Piece piece;
    Piece capture;

    public Move(Board board, Piece piece, int newcol, int newrow){

        this.oldcol = oldcol;
        this.oldrow = oldrow;
        this.newcol = newcol;
        this.newrow = newrow;

        this.piece = piece;
        this.capture = board.getpiece(newcol, newrow);
    }
}
