import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class List extends JFrame {
    JTextField textField;
    JButton button;
    JButton button1;


    static ArrayList <MyClass> arrayList = new ArrayList();

    static class MyClass {

        public String name;
        public int score;

        public MyClass(String name,int score) {
            this.name = name;
            this.score = score;
            arrayList.add(MyClass.this);
           // int fg = arrayList.size();
           // System.out.println(fg);

        }
    }

    List() {
        Container container = new Container();
        container.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(558,309);
        setSize(new Dimension(250,150));
        setVisible(true);

        textField = new JTextField();
        JLabel label = new JLabel("Введите NickName");
        button = new JButton("Сохранить");
        button1 = new JButton("Подтвердить");
        label.setBounds(0,0,130,30);
        textField.setBounds(10,40,100,20);
        button.setBounds(120,40,100,20);
        button1.setBounds(60,80,120,20);
        //int fg = arrayList.size();
      //  System.out.println(fg);


        container.add(label);
        container.add(textField);
        container.add(button);
        container.add(button1);
        add(container);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyClass myClass = new MyClass(textField.getText(),GameField.score);
                File file = new File("Example.txt");
                try {
                    file.createNewFile();
                    FileWriter writer = new FileWriter(file, true);
                    writer.write(textField.getText()+" ");
                    writer.write(String.valueOf(GameField.score)+"\n");
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


                  GameField.score = 0;}



        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public static void readTableFromFile(JTable table,File file) throws IOException,
            ClassNotFoundException {
        FileInputStream fos = null;
        try {
            fos = new FileInputStream(file);
            XMLDecoder decoder = new XMLDecoder(fos);
            table = (JTable) decoder.readObject();
            decoder.close();
        } finally {
            fos.close();
        }
    }

}