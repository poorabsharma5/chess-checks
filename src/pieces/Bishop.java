package pieces;

import main.Board;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Bishop extends Piece{
    public Bishop(Board board, int col, int row, boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tilesize;
        this.ypos = row * board.tilesize;
        this.isWhite=isWhite;
        this.name="Bishop";
        this.sprite = sheet.getSubimage(2 * sheetscale, isWhite? 0:sheetscale,sheetscale,sheetscale).getScaledInstance(board.tilesize, board.tilesize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isvalidmovement(int col, int row) {
        return Math.abs(this.col - col) == Math.abs(this.row - row);
    }

    public boolean movecollides(int col, int row){

        //upleft
        if(this.col > col && this.row > row){
            for (int i = 1; i< Math.abs(this.col - col);i++){
                if(board.getpiece(this.col- i, this.row - i) != null){
                    return true;
                }
            }
        }

        //upright
        if(this.col < col && this.row > row){
            for (int i = 1; i< Math.abs(this.col - col);i++){
                if(board.getpiece(this.col+ i, this.row - i) != null){
                    return true;
                }
            }
        }



        //downleft
        if(this.col > col && this.row < row){
            for (int i = 1; i< Math.abs(this.col - col);i++){
                if(board.getpiece(this.col- i, this.row + i) != null){
                    return true;
                }
            }
        }

        //downright
        if(this.col < col && this.row < row){
            for (int i = 1; i< Math.abs(this.col - col);i++){
                if(board.getpiece(this.col+ i, this.row + i) != null){
                    return true;
                }
            }
        }

        return false;
    }

}
