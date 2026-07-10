import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;


public class SNAKE{

    int ROWS = 25;
    int COLS = 25;
    int TILE_SIZE = 25;

    int WINDOW_WIDTH = TILE_SIZE * COLS;
    int WINDOW_HEIGHT = TILE_SIZE * ROWS;


    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    int snake = Tile(5*TILE_SIZE,5*TILE_SIZE);





    SNAKE(){
        frame.setVisible(true);
        frame.setTitle("Snake");
        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setBackground(Color.BLACK);
        frame.add(panel);
    }

    int Tile(int x, int y){
        return x + y;


    }
}