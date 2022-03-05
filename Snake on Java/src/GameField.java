import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.Random;


public class GameField extends JPanel implements ActionListener{

    private final int SIZE_X = 80;
    private final int SIZE_Y = 48;
    private final int SIZE_X1 = 544;
    private final int SIZE_Y2 = 368;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 100;
    final int min_x = 5; // Минимальное число для диапазона
    final int max_x = 34; // Максимальное число для диапазона
    final int min_y = 3; // Минимальное число для диапазона
    final int max_y = 23; // Максимальное число для диапазона
    private Image dot;
    private Image head;
    private Image apple;
    private Image Tail;
    private Image finish;
    private Image fon;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private  Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    static AffableThread2 mTwoThread;
    static AffableThread4 mFourThread;
    static int score=0;
    int a = 150;
    JLabel labelscore;




    public GameField(){
        setBackground(Color.white);
          mTwoThread = new AffableThread2();
          mTwoThread.start();
          setLayout(null);




        Score();
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }




    public void initGame()  {

        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 112 - i*DOT_SIZE;
            y[i] = 112;

        }

        timer = new Timer(150,this);
        timer.start();
            createApple();

    }



    public void createApple(){


        appleX = rnd(min_x,max_x)*DOT_SIZE;
        appleY =rnd(min_y,max_y)*DOT_SIZE;


    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
    public void loadImages(){
        ImageIcon iia = new ImageIcon(".idea/apple.png");
        apple = iia.getImage();
        ImageIcon lok = new ImageIcon(".idea/tail.png");
        Tail = lok.getImage();
        ImageIcon iid = new ImageIcon(".idea/dots.png");
        dot = iid.getImage();
        ImageIcon fin = new ImageIcon(".idea/finish.png");
        finish = fin.getImage();
        ImageIcon oak = new ImageIcon(".idea/FieldForSnake.png");
        fon = oak.getImage();
        ImageIcon  jkl =new ImageIcon(".idea/head2.png");
        head = jkl.getImage();



    }



    public void Score(){
        Font font = new Font("Verdana", Font.PLAIN, 12);
        font = new Font(null, Font.BOLD, 15);
        labelscore = new JLabel("Score: "+ score);
        labelscore.setBounds(290,-37,100,100);
        labelscore.setFont(font);
        add(labelscore);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(fon,0,0,640,420,this);
            g.drawImage(apple,appleX,appleY,this);
            g.drawImage(head,x[0],y[0],this);
            for (int i = 1; i < dots; i++) {
                g.drawImage(dot,x[i],y[i],this);
            }
            g.drawImage(Tail,x[dots],y[dots],this);
        }


    }

    public void move(){

        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        } if(up){
            y[0] -= DOT_SIZE;
        } if(down){
            y[0] += DOT_SIZE;
        }
    }



    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){

            dots++;
            score+=5;
            a-=5;
            timer.stop();
            timer = new Timer(a,this);
            timer.start();
            labelscore.setText("Score: "+String.valueOf(score));
            mFourThread = new AffableThread4();
            mFourThread.start();
            createApple();

        }
    }

    public void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
                mTwoThread.stop();
                Finish nm = new Finish();


            }
        }

        if(x[0]<SIZE_X && x[0]<SIZE_Y){
            inGame = false;
            mTwoThread.stop();
            Finish nm = new Finish();
        }
        if(x[0]<SIZE_X){
            inGame = false;
            mTwoThread.stop();
            Finish nm = new Finish();
            List jk = new List();
        }
        if(x[0]>SIZE_X1){
            inGame = false;
             mTwoThread.stop();
            Finish nm = new Finish();

        }
        if(y[0]>SIZE_X1 && y[0]>SIZE_Y2){
            inGame = false;
            mTwoThread.stop();
            Finish nm = new Finish();

        }

        if(y[0]<SIZE_Y){
            inGame = false;
            mTwoThread.stop();
            Finish nm = new Finish();

        }
        if(y[0]>SIZE_Y2){
            inGame = false;
            mTwoThread.stop();
           Finish nm = new Finish();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                ImageIcon you = new ImageIcon(".idea/head1.png");
                ImageIcon lok = new ImageIcon(".idea/tail3.png");
                Tail = lok.getImage();
                head = you.getImage();
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                ImageIcon you = new ImageIcon(".idea/head2.png");
                head = you.getImage();
                ImageIcon lok = new ImageIcon(".idea/tail.png");
                Tail = lok.getImage();

                right = true;
                up = false;
                down = false;
            }

            if(key == KeyEvent.VK_UP && !down){
                ImageIcon you = new ImageIcon(".idea/head3.png");
                ImageIcon lok = new ImageIcon(".idea/tail4.png");
                Tail = lok.getImage();
                head = you.getImage();
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                ImageIcon you = new ImageIcon(".idea/head4.png");
                head = you.getImage();
                ImageIcon lok = new ImageIcon(".idea/tail2.png");
                Tail = lok.getImage();
                right = false;
                down = true;
                left = false;
            }
        }

    }


}

