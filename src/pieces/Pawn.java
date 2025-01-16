package pieces;

import main.Board;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Pawn extends Piece{
    public Pawn(Board board, int col, int row, boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tilesize;
        this.ypos = row * board.tilesize;
        this.isWhite=isWhite;
        this.name="Pawn";
        this.sprite = sheet.getSubimage(5 * sheetscale, isWhite? 0:sheetscale,sheetscale,sheetscale).getScaledInstance(board.tilesize, board.tilesize, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isvalidmovement(int col, int row) {
        int colorindex = isWhite? 1:-1;

        //1
        if(this.col == col && row == this.row - colorindex && board.getpiece(col,row) == null){
            return true;
        }
        //2
        if(isfirstmove && this.col == col && row == this.row - colorindex * 2 && board.getpiece(col,row) == null && board.getpiece(col,row + colorindex) == null){
            return true;
        }
        //capture left
        if(col == this.col - 1 && row == this.row - colorindex && board.getpiece(col,row) != null){
            return true;
        }
        //capture right
        if(col == this.col + 1 && row == this.row - colorindex && board.getpiece(col,row) != null){
            return true;
        }
        //enpassant left
        if(board.gettilenum(col,row) == board.enpassanttile && col == this.col - 1 && row == this.row - colorindex && board.getpiece(col, row + colorindex) != null){
            return true;
        }
        //enpassant right
        if(board.gettilenum(col,row) == board.enpassanttile && col == this.col + 1 && row == this.row - colorindex && board.getpiece(col, row + colorindex) != null){
            return true;
        }
        return false;
    }
}
