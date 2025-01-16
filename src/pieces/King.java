package pieces;

import main.Board;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class King extends Piece{
    public King(Board board, int col, int row, boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tilesize;
        this.ypos = row * board.tilesize;
        this.isWhite=isWhite;
        this.name="King";
        this.sprite = sheet.getSubimage(0, isWhite? 0:sheetscale,sheetscale,sheetscale).getScaledInstance(board.tilesize, board.tilesize, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isvalidmovement(int col, int row) {
        return (Math.abs((col - this.col) * (row - this.row)) == 1) || Math.abs(col - this.col) + Math.abs(row - this.row) == 1;
    }
}
