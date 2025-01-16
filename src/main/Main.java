package main;

import javax.swing.*;
import java.awt.*;

public class Main{

    public static void main(String[] args){


        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1920,1080));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Board board = new Board();


        frame.add(board);
    }
}