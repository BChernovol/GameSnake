import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class StartGame extends JFrame {
    static AffableThread mSecondThread;

        StartGame() {
        setTitle("Game Snake");
        mSecondThread = new AffableThread();	//Создание потока
        mSecondThread.start();
        JButton button = new JButton("Start");
        JButton button1 = new JButton("Setting");
        JButton button2 = new JButton("EXIT");


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 450);
        setResizable(false);
        setLocation(380, 150);


        setContentPane(new BgPanel());
        Container container = this.getContentPane();
        container.setLayout(null);
        button.setBounds(230, 150, 140,50);
        button1.setBounds(230, 205, 140,50);
        button2.setBounds(230, 260, 140,50);

        container.add(button);
        container.add(button1);
        container.add(button2);

       try
        {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("button.jpg");
            // URL input = classLoader.getResource("image.png"); // <-- You can use URL class too.
            BufferedImage image = ImageIO.read(input);

            button.setIcon(new ImageIcon(image));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
       try
        {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("button1.jpg");
            // URL input = classLoader.getResource("image.png"); // <-- You can use URL class too.
            BufferedImage image = ImageIO.read(input);

            button1.setIcon(new ImageIcon(image));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("button2.jpg");
            // URL input = classLoader.getResource("image.png"); // <-- You can use URL class too.
            BufferedImage image = ImageIO.read(input);

            button2.setIcon(new ImageIcon(image));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mSecondThread.stop();
                    dispose();
                  GameSnake io = new GameSnake();


            }

        });
       button1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               mSecondThread.stop();
               dispose();
               Setting qw = new Setting();
           }
       });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Ви дійсно хочите вийти?",
                        "Вийти",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION)
                {   mSecondThread.stop();
                    System.exit(1);
                } else
                {
                    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });



        setVisible(true);


    }
    class BgPanel extends JPanel {
        public void paintComponent(Graphics g) {
            Image im = null;
            try {
                im = ImageIO.read(new File("E:\\GameSnake\\snake.jpg"));
            } catch (IOException e) {
            }
            g.drawImage(im, 0, 0, null);
        }
    }
}

