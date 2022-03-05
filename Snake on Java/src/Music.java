import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class AffableThread extends Thread
{
    @Override
    public void run()	//Этот метод будет выполнен в побочном потоке
    {
        try{
            FileInputStream f = new FileInputStream("E:\\GameSnake\\MusicFon.mp3");
            Player player = new Player(f);
            player.play();
        }catch(FileNotFoundException | JavaLayerException e){
            e.printStackTrace();
        }
    }
}
class AffableThread2 extends Thread
{
    @Override
    public void run()	//Этот метод будет выполнен в побочном потоке
    {
        try{
            FileInputStream f = new FileInputStream("E:\\GameSnake\\ForField.mp3");
            Player player = new Player(f);
            player.play();
        }catch(FileNotFoundException | JavaLayerException e){
            e.printStackTrace();
        }
    }
}
class AffableThread3 extends Thread
{
    @Override
    public void run()	//Этот метод будет выполнен в побочном потоке
    {
        try{
            FileInputStream f = new FileInputStream("E:\\GameSnake\\GameOver.mp3");
            Player player = new Player(f);
            player.play();
        }catch(FileNotFoundException | JavaLayerException e){
            e.printStackTrace();
            e.toString();
        }
    }
}
class AffableThread4 extends Thread
{
    @Override
    public void run()	//Этот метод будет выполнен в побочном потоке
    {
        try{
            FileInputStream f = new FileInputStream("E:\\GameSnake\\Eat.mp3");
            Player player = new Player(f);
            player.play();
        }catch(FileNotFoundException | JavaLayerException e){
            e.printStackTrace();
        }
    }
}




