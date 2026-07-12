import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;


public class SNAKE{

    int ROWS = 25;
    int COLS = 25;
    int TILE_SIZE = 25;

    int WINDOW_WIDTH = TILE_SIZE * COLS;
    int WINDOW_HEIGHT = TILE_SIZE * ROWS;


    JFrame frame = new JFrame();
    GamePanel panel = new GamePanel();
    Random random = new Random();

    Tile snake = new Tile(5*TILE_SIZE,5*TILE_SIZE);
    Tile food = new Tile(10*TILE_SIZE,10*TILE_SIZE);
    ArrayList<Tile> snake_body = new ArrayList<>();
    int velocityX = 0;
    int velocityY = 0;
    boolean game_over = false;
    int score = 0;





    SNAKE(){

        frame.setTitle("Snake");
        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.add(panel);
        frame.pack();

        frame.setVisible(true);
        panel.requestFocusInWindow();

    }

    class Tile{
        int x;
        int y;

        Tile(int x, int y){
            this.x = x;
            this.y = y;

        }
    }





    class GamePanel extends JPanel{

        Timer timer;

        GamePanel(){
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e){
                    change_direction(e);
                }
            });
            setFocusable(true);
            timer = new Timer(100,e -> {
                move();
                repaint();
            });
            timer.start();
        }
        void change_direction(KeyEvent e){
            if(game_over){
                return;
            }

            if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1){
                velocityX = 0;
                velocityY = -1;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
                velocityX = 0;
                velocityY = 1;
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
                velocityX = -1;
                velocityY = 0;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
                velocityX = 1;
                velocityY = 0;
            }

        }

        void move(){
            if(game_over){
                return;
            }
            if (snake.x <0 || snake.x >= WINDOW_WIDTH || snake.y < 0 || snake.y >= WINDOW_HEIGHT){
                game_over = true;
                return;
            }

            for(Tile tile : snake_body){
                if (snake.x == tile.x && snake.y == tile.y ){

                    game_over = true;
                    return;
                }
            }
            if(snake.x == food.x && snake.y == food.y){
                snake_body.add(new Tile(food.x, food.y));
                food.x = random.nextInt(0,COLS-1)*TILE_SIZE;
                food.y = random.nextInt(0, ROWS-1)*TILE_SIZE;
                score++;
            }

            for(int i = snake_body.size()-1;i>-1;i--){
                Tile tile = snake_body.get(i);
                if(i == 0){
                    tile.x = snake.x;
                    tile.y = snake.y;
                }
                else {
                    Tile prev_tile = snake_body.get(i-1);
                    tile.x = prev_tile.x;
                    tile.y = prev_tile.y;

                }

            }
            snake.x += velocityX * TILE_SIZE;
            snake.y += velocityY * TILE_SIZE;
        }

        void draw(Graphics g){

            g.setColor(Color.red);
            g.fillRect(food.x,food.y, TILE_SIZE, TILE_SIZE);

            g.setColor(Color.green);
            g.fillRect(snake.x, snake.y, TILE_SIZE, TILE_SIZE);



            for(Tile tile : snake_body){
                g.setColor(Color.green);
                g.fillRect(tile.x, tile.y, TILE_SIZE,  TILE_SIZE);
            }
            if(game_over){
                g.setColor(Color.white);
                g.setFont(new Font("Arial", Font.PLAIN,20));
                g.drawString("Game Over : " + score,WINDOW_WIDTH/2,WINDOW_HEIGHT/2);
            }
            else{
                g.setColor(Color.white);
                g.setFont(new Font("Arial",Font.PLAIN,10));
                g.drawString("Score : "+score,30,20);
            }

        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            draw(g);
        }


    }


}