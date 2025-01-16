package pieces;

import main.Board;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Rook extends Piece{
    public Rook(Board board, int col, int row, boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tilesize;
        this.ypos = row * board.tilesize;
        this.isWhite=isWhite;
        this.name="Rook";
        this.sprite = sheet.getSubimage(4 * sheetscale, isWhite? 0:sheetscale,sheetscale,sheetscale).getScaledInstance(board.tilesize, board.tilesize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isvalidmovement(int col, int row) {
        return this.col == col || this.row == row;
    }

    public boolean movecollides(int col, int row){
        if (this.col > col){
            for(int c = this.col-1; c>col; c--){
                if (board.getpiece(c, this.row) != null){
                    return true;
                }
            }
        }

        if (this.col < col){
            for(int c = this.col+1; c<col; c++){
                if (board.getpiece(c, this.row) != null){
                    return true;
                }
            }
        }


        if (this.row > row){
            for(int c = this.row-1; c>row; c--){
                if (board.getpiece(this.col, c) != null){
                    return true;
                }
            }
        }

        if (this.row < row){
            for(int c = this.row+1; c<row; c++){
                if (board.getpiece(this.col, c) != null){
                    return true;
                }
            }
        }
    return false;
    }
}
