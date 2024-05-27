import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
public class SnakeGamePanel extends JPanel implements ActionListener {

    static final int WIDTH_OF_SCREEN = 800;
    static final int HEIGHT_OF_SCREEN = 800;
    static final int UNIT_SIZE = 15;
    static final int GAME_UNIT_SIZE = (HEIGHT_OF_SCREEN*WIDTH_OF_SCREEN)/UNIT_SIZE;
    static final int DELAY = 80;
    final int xCoOrdinate[] = new int [GAME_UNIT_SIZE];
    final int yCoOrdinate[] = new int [GAME_UNIT_SIZE];

    int snakeBody = 4;
    int foodEaten = 0;

    int foodX;
    int foodY;
    char direction = 'D';
    boolean snakeRunning = false;
    Timer timer;
    Random random;

    SnakeGamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH_OF_SCREEN, HEIGHT_OF_SCREEN));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new Key());
        gameStart();
    }

    public void gameStart(){
        newFood();
        snakeRunning = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics){
        if (snakeRunning) {
            graphics.setColor(Color.RED);
            graphics.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < snakeBody; i++) {
                if (i == 0) {
                    graphics.setColor(Color.YELLOW);
                    graphics.fillRect(xCoOrdinate[i], yCoOrdinate[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    graphics.setColor(new Color(45, 180, 0));
                    graphics.fillRect(xCoOrdinate[i], yCoOrdinate[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: "+foodEaten,
                    (WIDTH_OF_SCREEN-metrics.stringWidth("Score: "+foodEaten))/2,
                    graphics.getFont().getSize());

        }else{
            gameOver(graphics);
        }
    }


    public void newFood(){
        foodX = random.nextInt((int)WIDTH_OF_SCREEN/UNIT_SIZE)*UNIT_SIZE;
        foodY = random.nextInt((int)HEIGHT_OF_SCREEN/UNIT_SIZE)*UNIT_SIZE;

    }
    public void moveSnake(){
        for(int i = snakeBody; i>0; i--){
            xCoOrdinate[i] = xCoOrdinate[i-1];
            yCoOrdinate[i] = yCoOrdinate[i-1];
        }
        switch(direction) {
            case 'U':
                yCoOrdinate[0] = yCoOrdinate[0] - UNIT_SIZE;
                break;
            case 'D':
                yCoOrdinate[0] = yCoOrdinate[0] + UNIT_SIZE;
                break;
            case 'L':
                xCoOrdinate[0] = xCoOrdinate[0] - UNIT_SIZE;
                break;
            case 'R':
                xCoOrdinate[0] = xCoOrdinate[0] + UNIT_SIZE;
                break;
        }
    }

    public void gameOver(Graphics graphics){

        //displaying the score
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: "+foodEaten,
                (WIDTH_OF_SCREEN-metrics1.stringWidth("Score: "+foodEaten))/2,
                graphics.getFont().getSize());
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("GAME OVER",
                (WIDTH_OF_SCREEN-metrics.stringWidth("GAME OVER"))/2,
                HEIGHT_OF_SCREEN/2);

    }

    public void foodCheck(){
        if(xCoOrdinate[0]==foodX && yCoOrdinate[0]==foodY){
            snakeBody++;
            foodEaten+=10;
            newFood();
        }
    }

    public void crashCheck(){
        for(int i=snakeBody; i>0; i--){
            //gameOverCheck
            if((xCoOrdinate[0]==xCoOrdinate[i]&&yCoOrdinate[0]==yCoOrdinate[i])){
                snakeRunning = false;
            }

            if(xCoOrdinate[0]<0){
                snakeRunning = false;
            }

            if(xCoOrdinate[0]>WIDTH_OF_SCREEN){
                snakeRunning = false;
            }

            if(yCoOrdinate[0]<0){
                snakeRunning = false;
            }

            if(yCoOrdinate[0]>HEIGHT_OF_SCREEN){
                snakeRunning = false;
            }

            if(!snakeRunning){
                timer.stop();
            }

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(snakeRunning){
            moveSnake();
            foodCheck();
            crashCheck();
        }
        repaint();
    }
    class Key extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
            System.out.println("Direction: " + direction);  // Debugging line
        }
    }
}


