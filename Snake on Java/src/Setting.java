import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;


public class Setting extends JFrame {
    JScrollPane sp;
    JTable jt;
    Setting(){
        setTitle("Game Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640,450);
        JButton back = new JButton("Назад");

        setResizable(false);
        setLocation(380,150);

        setContentPane(new SetPanel());
        Container container = this.getContentPane();
        container.setLayout(null);
        back.setBounds(10,350,30,30);

         String data[][]= new String[100][2];
         String column[]={"NickName","Record"};



         try{
           FileReader fr = new FileReader(new File("Example.txt"));

               int k=0;
            //while(k!=3){
               char buffer[]=new char[10];
                data[k][0]= String.valueOf(buffer);
                fr.read(buffer);
                data[k][1]= String.valueOf(buffer);
                System.out.println(data[k][1]);
               k++;

          //  }

         }
         catch (IOException e){
             System.out.println(e.toString());
         }
        jt = new JTable(data,column);
        jt.setBounds(40,60,200,100);
        setVisible(true);
      //  container.add(sp);
        container.add(jt);
        container.add(back);
        setVisible(true);


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StartGame as = new StartGame();
            }
        });
       try
        {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("123.png");
            // URL input = classLoader.getResource("image.png"); // <-- You can use URL class too.
            BufferedImage image = ImageIO.read(input);

            back.setIcon(new ImageIcon(image));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    class SetPanel extends JPanel {
        public void paintComponent(Graphics g) {
            Image im = null;
            try {
                im = ImageIO.read(new File("E:\\GameSnake\\setting.jpg"));
            } catch (IOException e) {
            }
            g.drawImage(im, 0, 0, null);
        }
    }
}
