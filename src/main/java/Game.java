import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by UC198883 on 16/02/2016.
 */
public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private static int width = 300;
    private static int height = width / 16 * 9;
    private static int scale = 3;


    private Thread thread;
    private JFrame jFrame;
    private boolean running = false;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        jFrame = new JFrame();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    public void run() {
        while ( running ) {
            update();
            render();
        }
    }

    public void update() {

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs ==null){
            createBufferStrategy(3);
            return;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.jFrame.setResizable(false);
        game.jFrame.setTitle("Rain");
        game.jFrame.add(game);
        game.jFrame.pack();
        game.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.jFrame.setLocationRelativeTo(null);
        game.jFrame.setVisible(true);

        game.start();
    }
}
