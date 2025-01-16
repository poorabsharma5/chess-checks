package pieces;
import main.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {

    public int col, row;
    public int xpos, ypos;
    public boolean isWhite;
    public String name;
    public int value;

    BufferedImage sheet;
    {
        try {
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("pieces.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    protected int sheetscale = sheet.getWidth()/6;

    Image sprite;
    Board board;

    public Piece(Board board) {
        this.board = board;
    }

    public boolean isfirstmove = true;

    public boolean isvalidmovement(int col, int row){

        return true;
    }

    public boolean movecollides(int col, int row){

        return false;
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite, xpos, ypos, null);
    }
}
