import graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

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

    private Screen screen;

    private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);

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
        try {
            thread.sleep(300);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if ( bs == null ) {
            createBufferStrategy(3);
            return;
        }
        screen.render();

        for ( int i =0;i<pixels.length;i++ ){
            pixels[i]=screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
//        g.setColor(new Color((int) Math.ceil(Math.random() * 250), (int) Math.ceil(Math.random() * 250), (int) Math.ceil(Math.random() * 250)));
//        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
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
