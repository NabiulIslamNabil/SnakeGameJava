import javax.swing.JFrame;

public class SnakeGameFrame extends JFrame {
    public SnakeGameFrame() {
        this.add(new SnakeGamePanel(this));
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}