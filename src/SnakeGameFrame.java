import javax.swing.*;

public class SnakeGameFrame extends JFrame {

    SnakeGameFrame(){
        SnakeGamePanel gamePanel = new SnakeGamePanel();
        this.add(gamePanel);
        this.setTitle("SNAKE MANIA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
