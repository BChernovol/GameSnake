import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class GameSnake  extends JFrame{
    public GameSnake(){
        setTitle("Game Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(640,450);
        setResizable(false);
        setLocation(380,150);
        add(new GameField());
        setVisible(true);
    }


    public static void main(String[] args) {
        StartGame hu = new StartGame();
    }
}



