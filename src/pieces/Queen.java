package pieces;

import main.Board;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Queen extends Piece{
    public Queen(Board board, int col, int row, boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tilesize;
        this.ypos = row * board.tilesize;
        this.isWhite=isWhite;
        this.name="Queen";
        this.sprite = sheet.getSubimage(sheetscale, isWhite? 0:sheetscale,sheetscale,sheetscale).getScaledInstance(board.tilesize, board.tilesize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isvalidmovement(int col, int row) {
        return this.col == col || this.row == row || Math.abs(this.col - col) == Math.abs(this.row - row);
    }

    public boolean movecollides(int col, int row){
        if (this.col == col || this.row == row) {

            if (this.col > col) {
                for (int c = this.col - 1; c > col; c--) {
                    if (board.getpiece(c, this.row) != null) {
                        return true;
                    }
                }
            }

            if (this.col < col) {
                for (int c = this.col + 1; c < col; c++) {
                    if (board.getpiece(c, this.row) != null) {
                        return true;
                    }
                }
            }


            if (this.row > row) {
                for (int c = this.row - 1; c > row; c--) {
                    if (board.getpiece(this.col, c) != null) {
                        return true;
                    }
                }
            }

            if (this.row < row) {
                for (int c = this.row + 1; c < row; c++) {
                    if (board.getpiece(this.col, c) != null) {
                        return true;
                    }
                }
            }
        }
        else{
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
        }
            return false;
    }
}
