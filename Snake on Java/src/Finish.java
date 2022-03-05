import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Finish extends JFrame{
    static AffableThread3 mFreeThread;


   public Finish(){
       setTitle("Game Snake");
       mFreeThread = new AffableThread3();
       mFreeThread.start();



       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       setSize(640,450);
       setResizable(false);
       setLocation(380,150);
       setContentPane(new BgPanel());
       Container container = this.getContentPane();
       container.setLayout(null);
       JButton restart = new JButton("restart");
       JButton home = new JButton("Menu");
       home.setBounds(470,290,65,57);
       container.add(home);
       try
       {
           ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
           InputStream input = classLoader.getResourceAsStream("home.jpg");
           // URL input = classLoader.getResource("image.png"); // <-- You can use URL class too.
           BufferedImage image = ImageIO.read(input);

           home.setIcon(new ImageIcon(image));
       }
       catch(IOException e)
       {
           e.printStackTrace();
       }
       home.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dispose();
               StartGame bn = new StartGame();
           }
       });
       restart.setBounds(80,290,65,57);
       container.add(restart);
       try
       {
           ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
           InputStream input = classLoader.getResourceAsStream("restart.jpg");
           // URL input = classLoader.getResource("image.png"); // <-- You can use URL class too.
           BufferedImage image = ImageIO.read(input);

           restart.setIcon(new ImageIcon(image));
       }
       catch(IOException e)
       {
           e.printStackTrace();
       }
       restart.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dispose();
               GameSnake io =  new GameSnake();
           }
       });
       setVisible(true);

    }

    class BgPanel extends JPanel {
        public void paintComponent(Graphics g) {
            Image im = null;
            try {
                im = ImageIO.read(new File("E:\\GameSnake\\finish.jpg"));
            } catch (IOException e) {
            }
            g.drawImage(im, 0, 0, null);
        }
    }
}
